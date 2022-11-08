package com.vincent.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.template.entity.FieldTemplate;
import com.vincent.template.entity.Template;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xujiangtao
 * @since 2022-02-16
 */
public interface ITemplateService extends IService<Template> {

    /**
     * 添加修改自定义字段模板
     *
     * @param fieldList 自定义字段列表
     * @param type      字段数据
     * @return 是否添加
     */
    boolean addField(List<FieldTemplate> fieldList, Integer type);

    /**
     * 更修当前自定义字段
     *
     * @param field 自定义字段实体
     * @param type  类型
     * @return 是否修改
     */
    boolean editField(FieldTemplate field, Integer type);

    /**
     * 删除当前自定义字段
     *
     * @param fieldName 自定义字段名称
     * @param type      类型
     * @return 是否删除
     */
    boolean removeField(String fieldName, Integer type);

    /**
     * 通过类型查询自定义字段模板
     *
     * @param type 数据指定类型  1：客户自定义字段       2：跟进任务
     * @return 自定义字段模板
     */
    Template queryByType(Integer type);

    /**
     * 通过类型查询自定义字段模板内容实体
     *
     * @param type 数据指定类型  1：客户自定义字段       2：跟进任务
     * @return 自定义字段模板内容实体集合
     */
    List<FieldTemplate> queryTempObjByType(Integer type);

    /**
     * 查询选项
     *
     * @param fieldName 自定义字段名称
     * @param type      类型 数据指定类型  1：客户自定义字段       2：跟进任务
     * @return 选项
     */
    List<Map<Object, Object>> queryOption(String fieldName, Integer type);

    /**
     * 查询自定义字段数据集合
     *
     * @param type  数据指定类型  1：客户自定义字段       2：跟进任务
     * @param objId 具体数据列表id
     * @return 自定义字段数据集合
     */
    List<FieldTemplate> queryFieldData(Integer type, Long objId);

    /**
     * 保存或修改自定义字段数据
     *
     * @param fieldTemplateList 自定义字段模板集合
     * @param objId             数据主键id
     * @param type              关联的自定义字段类型 （1：客户自定义字段       2：跟进任务）
     * @return 是否保存成功
     */
    boolean saveOrUpdateFieldData(List<FieldTemplate> fieldTemplateList, Long objId, Integer type);

}
