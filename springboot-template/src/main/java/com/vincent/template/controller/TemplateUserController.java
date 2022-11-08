package com.vincent.template.controller;


import com.vincent.template.entity.FieldTemplate;
import com.vincent.template.entity.Result;
import com.vincent.template.entity.TemplateUser;
import com.vincent.template.service.ITemplateService;
import com.vincent.template.service.ITemplateUserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vincent
 */
@RestController
@RequestMapping("/template/user")
public class TemplateUserController {

    private ITemplateUserService iUserService;

    private ITemplateService iTemplateService;

    @PostMapping("/list")
    public Result list(){
        return Result.success(iUserService.list());
    }

    @GetMapping("/{id}")
    public Result info(@PathVariable Long id){
        TemplateUser user = iUserService.getById(id);
        List<FieldTemplate> fieldTemplates = iTemplateService.queryFieldData(1, id);
        user.setFieldTemplateList(fieldTemplates);
        return Result.success(user);
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody TemplateUser user){
        if (user.getUserName().length()>20 || user.getPwd().length()>20 || user.getAge()>1000){
            return Result.failed("数据量过大，请输入合理测试数据");
        }
        if (user.getId()==null){
            int count = iUserService.count();
            if (count>9){
                return Result.failed("测试数据长度最大不超过10条");
            }
        }
        boolean save = iUserService.saveOrUpdate(user);
        if (save && user.getFieldTemplateList()!=null && user.getFieldTemplateList().size()>0){
            iTemplateService.saveOrUpdateFieldData(user.getFieldTemplateList(), user.getId(),1);
        }
        return Result.success(save);
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable Long id){
        return Result.success(iUserService.removeById(id));
    }
}
