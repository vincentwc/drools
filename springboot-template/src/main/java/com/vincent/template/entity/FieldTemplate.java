package com.vincent.template.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @description: 自定义字段模板转json实体对象
 * @author: xujt
 * @date: 2022-02-14 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldTemplate {
    /**
     * 字段key名
     */
    private String field;

    /**
     * 字段显示名
     */
    private String label;

    /**
     * 是否必填
     */
    private boolean required;

    /**
     * 1：自字符串类型     2：boolean类型     3：日期时间类型    4：int类型 5:单选 6：多选
     */
    private Integer type;

    /**
     * 单选或多选选项
     */
    private List<Map<Object,Object>> map;

    /**
     * 用于接受前端编辑自定义字段设置的值
     */
    private Object value;
}
