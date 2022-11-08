package com.vincent.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author vincent
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FieldTemplate implements Serializable {

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
