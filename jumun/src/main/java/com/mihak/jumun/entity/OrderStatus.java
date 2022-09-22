package com.mihak.jumun.entity;

public enum OrderStatus {
    BEFOREORDER("주문접수전"), ACCEPTIONORDER("주문접수중"), COOKING("조리중"),
    COMPLETE("조리완료"), CANCEL("주문취소");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
