package com.vincent.template.controller;


import com.alibaba.fastjson.JSONArray;
import com.vincent.template.entity.FieldTemplate;
import com.vincent.template.entity.Result;
import com.vincent.template.entity.Template;
import com.vincent.template.entity.TemplateUser;
import com.vincent.template.entity.TemplateVo;
import com.vincent.template.service.ITemplateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xujiangtao
 * @since 2022-02-16
 */
@RestController
@RequestMapping("/field/template")
@AllArgsConstructor
public class TemplateController {

    private ITemplateService iTemplateService;

    @GetMapping(value = "/type/{type}")
    public Result getFieldTemplate(@PathVariable("type") Integer type) {
        Template crmFieldTemplate = iTemplateService.queryByType(type);
        if (crmFieldTemplate == null) {
            return Result.success(new ArrayList<>());
        }
        List<FieldTemplate> fieldList = JSONArray.parseArray(
                iTemplateService.queryByType(type).getTemplate(), FieldTemplate.class);
        if (fieldList == null) {
            fieldList = new ArrayList<>();
        }
        return Result.success(fieldList);
    }

    @PostMapping("/addField")
    public Result addField(@RequestBody TemplateVo templateVo) {
        return Result.success(iTemplateService.addField(
                templateVo.getFieldTemplateList(), templateVo.getType()));
    }

    @DeleteMapping("/{field}/{type}")
    public Result deleteField(@PathVariable String field, @PathVariable Integer type) {
        return Result.judge(iTemplateService.removeField(field, type));
    }


    @GetMapping("option/{field}/{type}")
    public Result queryOption(@PathVariable String field, @PathVariable Integer type) {
        return Result.success(iTemplateService.queryOption(field, type));
    }

    @GetMapping("/{type}")
    public Result queryFieldTemplate(@PathVariable Integer type) {
        TemplateUser user = new TemplateUser();
        List<FieldTemplate> fieldTemplates = iTemplateService.queryTempObjByType(type);
        user.setFieldTemplateList(fieldTemplates);
        return Result.success(user);
    }

}
