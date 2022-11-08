package com.vincent.template.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: crm
 * @description:
 * @author: xujt
 * @date: 2022-02-14 14:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateVo {
    List<FieldTemplate> fieldTemplateList;
    Integer type;
}
