package com.vincent;

import com.vincent.entity.Order;
import com.vincent.service.impl.RuleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootDroolsApplicationTests {

    @Autowired
    private RuleServiceImpl ruleService;

    @Test
    void contextLoads() {
        Order order = new Order();
        order.setAmount(105);
        order.setScore(13);
        ruleService.executeRule(order);
    }

}
