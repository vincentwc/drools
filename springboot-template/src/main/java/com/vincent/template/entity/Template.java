package com.vincent.template.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author xujiangtao
 * @since 2022-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("field_template")
public class Template implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 自定义字段关联表类型（1：客户，2：跟进任务）
     */
    private Integer type;

    /**
     * json自定义字段模板
     */
    private String template;

    /**
     * 备注
     */
    private String remark;


}
