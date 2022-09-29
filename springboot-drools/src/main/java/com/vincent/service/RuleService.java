package com.vincent.service;

import com.vincent.entity.Order;

/**
 * @author wang_cheng
 * @date 2022/09/28 18:38
 * @desc
 **/
public interface RuleService {

    Order executeRule(Order order);
}
