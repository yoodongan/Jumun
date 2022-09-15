package com.mihak.jumun.entity;

public enum OrderType {

    FORHERE("먹고가기"), TAKEOUT("가져가기");

    private final String description;

    OrderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}