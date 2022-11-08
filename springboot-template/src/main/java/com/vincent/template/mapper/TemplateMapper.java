package com.vincent.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vincent.template.entity.Template;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xujiangtao
 * @since 2022-02-16
 */
public interface TemplateMapper extends BaseMapper<Template> {

    /**
     * 通过type查询自定义字段模板
     *
     * @param type
     * @return
     */
    Template queryByType(Integer type);
}
