package com.mihak.jumun.entity;

public enum PayType {
    CASH("현금"), KAKAOPAY("카카오페이"), NAVERPAY("네이버페이");

    private final String description;

    PayType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
