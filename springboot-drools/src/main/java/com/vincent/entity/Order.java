package com.vincent.entity;

/**
 * @author wang_cheng
 * @date 2022/09/27 14:32
 * @desc
 **/
public class Order {

    /**
     * 原始订单金额
     */
    private int amount;

    /**
     * 积分
     */
    private int score;

    public void setAmount() {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
