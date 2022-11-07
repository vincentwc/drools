package com.vincent.drools.entity;

import java.util.List;

/**
 * @author wang_cheng
 * @date 2022/09/28 16:06
 * @desc
 **/
public class Customer {

    private String name;

    private List<Order> orderList;


    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
