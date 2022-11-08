package com.vincent.template.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.template.entity.FieldData;
import com.vincent.template.entity.FieldTemplate;
import com.vincent.template.entity.Template;
import com.vincent.template.mapper.TemplateMapper;
import com.vincent.template.service.IFieldDataService;
import com.vincent.template.service.ITemplateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字段模板Service业务层处理
 *
 * @author xjt
 * @date 2022-02-14
 */
@Service
@AllArgsConstructor
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateService {

    @Resource
    private IFieldDataService iDataService;

    /**
     * 添加修改自定义字段模板
     *
     * @param fieldList 自定义字段列表
     * @param type      字段数据
     * @return 是否保存成功
     */
    @Override
    public boolean addField(List<FieldTemplate> fieldList, Integer type) {
        List<String> label = fieldList.stream().map(FieldTemplate::getLabel).collect(Collectors.toList());
        List<String> field = fieldList.stream().map(FieldTemplate::getField).collect(Collectors.toList());
        Set<String> test = new HashSet<>(field);
        if (field.size() != test.size()) {
            log.debug("字段名存在重复");
            return false;
        }
        Set<String> testLabel = new HashSet<>(label);
        if (testLabel.size() != test.size()) {
            log.debug("显示名存在重复");
            return false;
        }
        Template crmFieldTemplate = queryByType(type);
        if (crmFieldTemplate == null) {
            crmFieldTemplate = new Template();
            crmFieldTemplate.setTemplate(JSON.toJSONString(fieldList));
            crmFieldTemplate.setType(type);
            int insert = getBaseMapper().insert(crmFieldTemplate);
            return insert > 0;
        } else {
            crmFieldTemplate.setTemplate(JSON.toJSONString(fieldList));
            int update = getBaseMapper().updateById(crmFieldTemplate);
            return update > 0;
        }
    }

    /**
     * 更修当前自定义字段
     *
     * @param field 自定义字段实体
     * @param type  类型
     * @return 是否更修成功
     */
    @Override
    public boolean editField(FieldTemplate field, Integer type) {
        Template crmFieldTemplate = queryByType(type);
        List<FieldTemplate> fieldList = JSON.parseArray(crmFieldTemplate.getTemplate(), FieldTemplate.class);
        for (int i = 0; i < fieldList.size(); i++) {
            if (field.getField().equals(fieldList.get(i).getField())) {
                fieldList.set(i, field);
                return true;
            }
        }
        return false;
    }

    /**
     * 删除当前自定义字段
     *
     * @param fieldName 自定义字段名称
     * @param type      类型
     * @return 是否删除
     */
    @Override
    public boolean removeField(String fieldName, Integer type) {
        Template crmFieldTemplate = queryByType(type);
        if (crmFieldTemplate != null) {
            List<FieldTemplate> fieldList = JSON.parseArray(crmFieldTemplate.getTemplate(), FieldTemplate.class);
            int initial = fieldList.size();
            for (int i = 0; i < fieldList.size(); i++) {
                String field = fieldList.get(i).getField();
                if (field.equals(fieldName)) {
                    fieldList.remove(i);
                    break;
                }
            }
            if (initial != fieldList.size()) {
                crmFieldTemplate.setTemplate(JSON.toJSONString(fieldList));
                int i = getBaseMapper().updateById(crmFieldTemplate);
                return i > 0;
            }
        }
        return false;
    }

    /**
     * 通过类型查询自定义字段模板
     *
     * @param type 类型
     * @return 自定义字段模板
     */
    @Override
    public Template queryByType(Integer type) {
        return getBaseMapper().queryByType(type);
    }

    /**
     * 通过类型查询自定义字段模板内容实体
     *
     * @param type 类型
     * @return 自定义字段模板
     */
    @Override
    public List<FieldTemplate> queryTempObjByType(Integer type) {
        Template crmFieldTemplate = queryByType(type);
        List<FieldTemplate> fieldList = new ArrayList<>();
        if (crmFieldTemplate != null) {
            fieldList = JSON.parseArray(crmFieldTemplate.getTemplate(), FieldTemplate.class);
        }
        return fieldList;
    }

    @Override
    public List<Map<Object, Object>> queryOption(String fieldName, Integer type) {
        List<FieldTemplate> fieldTemplates = queryTempObjByType(type);
        for (FieldTemplate fieldTemplate : fieldTemplates) {
            String field = fieldTemplate.getField();
            if (field.equals(fieldName)) {
                return fieldTemplate.getMap();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<FieldTemplate> queryFieldData(Integer type, Long objId) {
        List<FieldTemplate> fieldTemplates = this.queryTempObjByType(type);
        QueryWrapper<FieldData> wrapper = new QueryWrapper<>();
        wrapper.eq("obj_id", objId);
        FieldData one = iDataService.getOne(wrapper);
        if (one != null && fieldTemplates != null && !fieldTemplates.isEmpty()) {
            Map map = JSON.parseObject(one.getFieldData(), Map.class);
            for (FieldTemplate fieldTemplate : fieldTemplates) {
                Object data = map.get(fieldTemplate.getField());
                if (data != null && data.toString().contains("[") && data.toString().contains("]")) {
                    String replace = data.toString().replace("[", "").replace("]", "");
                    if (replace.length() > 0) {
                        String[] list = replace.split(",");
                        fieldTemplate.setValue(Arrays.asList(list));
                    }
                } else {
                    fieldTemplate.setValue(data);
                }
            }
        }
        return fieldTemplates;
    }

    @Override
    public boolean saveOrUpdateFieldData(List<FieldTemplate> fieldTemplateList, Long objId, Integer type) {
        Map<String, String> map = new HashMap<>();
        if (fieldTemplateList != null && !fieldTemplateList.isEmpty()) {
            for (FieldTemplate fieldTemplate : fieldTemplateList) {
//                如果为必填项，并且值为空，则取消保存
                if (fieldTemplate.isRequired() &&
                        (fieldTemplate.getValue() == null ||
                                fieldTemplate.getValue() == "" ||
                                "null".equals(fieldTemplate.getValue())
                        )) {
//                    有必填字段未输入
                    return false;
                }
                map.put(fieldTemplate.getField(), fieldTemplate.getValue() == null ? null :
                        fieldTemplate.getValue().toString().replace(", ", ","));
            }
        } else {
//            没有自定义字段
            return true;
        }
        Template crmFieldTemplate = this.queryByType(type);
        QueryWrapper<FieldData> wrapper = new QueryWrapper<>();
        wrapper.eq("template_id", crmFieldTemplate.getId());
        wrapper.eq("obj_id", objId);
        FieldData fieldData = iDataService.getOne(wrapper);
        if (fieldData == null) {
            fieldData = new FieldData();
            fieldData.setTemplateId(crmFieldTemplate.getId());
            fieldData.setObjId(objId);
            fieldData.setFieldData(JSON.toJSONString(map));
            return iDataService.save(fieldData);
        } else {
            fieldData.setFieldData(JSON.toJSONString(map));
            return iDataService.updateById(fieldData);
        }
    }
}
