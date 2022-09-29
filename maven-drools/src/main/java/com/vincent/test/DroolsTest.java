package com.vincent.test;

import com.vincent.entity.Customer;
import com.vincent.entity.Order;
import com.vincent.entity.Person;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang_cheng
 * @date 2022/09/28 15:03
 * @desc
 **/
public class DroolsTest {


    @Test
    public void test1() {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();

        Order order = new Order();
        order.setAmount(255);
        // 4. insert fact
        kieSession.insert(order);
        // 5. 触发规则
        kieSession.fireAllRules();
        // 6. 关闭session
        kieSession.dispose();
    }

    @Test
    public void test2() {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();

        Order order = new Order();
        Customer customer = new Customer();
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        customer.setName("张三丰");
        customer.setOrderList(orderList);

        // 4. insert fact
        kieSession.insert(order);
        kieSession.insert(customer);
        // 5. 触发规则   指定执行rule_3这个规则
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_3"));
        // 6. 关闭session
        kieSession.dispose();
    }


    @Test
    public void test3() {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();
        Customer customer = new Customer();
        customer.setName("张三");
        kieSession.insert(customer);
        // 5. 触发规则
        kieSession.fireAllRules();
        // 6. 关闭session
        kieSession.dispose();
    }


    @Test
    public void test4() {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();
        Customer customer = new Customer();
        customer.setName("李四");
        kieSession.insert(customer);
        // 5. 触发规则
        kieSession.fireAllRules();
        // 6. 关闭session
        kieSession.dispose();
    }


    @Test
    public void test5() {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();
        Customer customer = new Customer();
        customer.setName("李四");
        kieSession.insert(customer);
        // 5. 触发规则
        kieSession.fireAllRules();
        // 6. 关闭session
        kieSession.dispose();
    }


    @Test
    public void test6() {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();

        // 5. 触发规则
        kieSession.fireAllRules();
        // 6. 关闭session
        kieSession.dispose();
    }

    @Test
    public void test7() {
        // 设置drools日期格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd");
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();

//        kieSession.getAgenda().getAgendaGroup("myagendagroup_1").setFocus();
        // 5. 触发规则
        kieSession.fireAllRules();
        // 6. 关闭session
        kieSession.dispose();
    }

    @Test
    public void test8() throws InterruptedException {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();

        new Thread(() -> kieSession.fireUntilHalt()).start();
        Thread.sleep(10000);

        kieSession.halt();
        // 5. 触发规则
//        kieSession.fireAllRules();
        // 6. 关闭session
        kieSession.dispose();
    }

    @Test
    public void test9() {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();
        // 设置一个全局变量
        List<String> list = new ArrayList();
        kieSession.setGlobal("myGlobalList", list);
        // 5. 触发规则
        kieSession.fireAllRules();
        // 6. 关闭session
        kieSession.dispose();

        System.out.println("规则执行完之后，全局变量list的元数个数" + list.size());
    }

    @Test
    public void test10() {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();
        Person p1 = new Person();
        p1.setName("jack1");
        p1.setAge(32);
        kieSession.insert(p1);

        Person p2 = new Person();
        p2.setName("jack2");
        p2.setAge(60);
        kieSession.insert(p2);

        // 5. 触发规则
        kieSession.fireAllRules();

        // 执行查询
        QueryResults results = kieSession.getQueryResults("query_1");
        for (QueryResultsRow resultsRow : results) {
            Person p = (Person) resultsRow.get("$person");
            System.out.println(p);
        }
        System.out.println("==============");
        QueryResults results2 = kieSession.getQueryResults("query_2","jack1");
        for (QueryResultsRow resultsRow : results2) {
            Person p = (Person) resultsRow.get("$person");
            System.out.println(p);
        }

        // 6. 关闭session
        kieSession.dispose();
    }


    @Test
    public void test11() {
        // 1. 获取kieServices
        KieServices kieServices = KieServices.Factory.get();
        // 2. 获取container
        KieContainer container = kieServices.getKieClasspathContainer();
        // 3. kieSession
        KieSession kieSession = container.newKieSession();
        Person p1 = new Person();
        p1.setName("jack1");
        p1.setAge(32);
        kieSession.insert(p1);

        // 5. 触发规则
        kieSession.fireAllRules();


        // 6. 关闭session
        kieSession.dispose();
    }
}
