package com.vincent.service.impl;

import com.vincent.entity.Order;
import com.vincent.service.RuleService;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wang_cheng
 * @date 2022/09/28 18:28
 * @desc
 **/
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private KieBase kieBase;


    @Override
    public Order executeRule(Order order) {
        KieSession kieSession = kieBase.newKieSession();
        // 插入实施对象
        kieSession.insert(order);
        // 执行所有的规则
        kieSession.fireAllRules();

        kieSession.dispose();

        return order;
    }
}
