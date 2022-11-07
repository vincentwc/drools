package com.vincent.drools;

import com.vincent.drools.entity.Order;
import com.vincent.drools.service.impl.RuleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootDroolsApplicationTests {

    @Resource
    private RuleServiceImpl ruleService;

    @Test
    void contextLoads() {
        Order order = new Order();
        order.setAmount(105);
        order.setScore(13);
        ruleService.executeRule(order);
    }

}
