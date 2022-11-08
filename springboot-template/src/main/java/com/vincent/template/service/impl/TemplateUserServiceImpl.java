package com.vincent.template.service.impl;

import com.vincent.template.entity.TemplateUser;
import com.vincent.template.mapper.TemplateUserMapper;
import com.vincent.template.service.ITemplateUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author vincent
 */
@Service
public class TemplateUserServiceImpl extends ServiceImpl<TemplateUserMapper, TemplateUser> implements ITemplateUserService {

}
