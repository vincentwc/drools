package com.vincent.drools.controller;

import com.vincent.drools.entity.Order;
import com.vincent.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wang_cheng
 * @date 2022/09/28 18:30
 * @desc
 **/
@RestController
public class OrderController {

    @Autowired
    private RuleService ruleService;

    @GetMapping("saveOrder")
    public Order saveOrder(Order order){
        return ruleService.executeRule(order);
    }
}
