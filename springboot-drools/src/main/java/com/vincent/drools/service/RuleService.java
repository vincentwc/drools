package com.vincent.drools.service;

import com.vincent.drools.entity.Order;

/**
 * @author wang_cheng
 * @date 2022/09/28 18:38
 * @desc
 **/
public interface RuleService {

    Order executeRule(Order order);
}
