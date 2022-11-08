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
public class FieldData implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字段模板id
     */
    private Integer templateId;

    /**
     * 模板字段数据
     */
    private String fieldData;

    /**
     * 备注
     */
    private String remark;

    /**
     * 具体数据id
     */
    private String objId;


}
