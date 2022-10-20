package com.mihak.jumun.pay.entity.enumuration;

public enum PayStatus {
    BEFORE("결제전"), CONTINUE("진행중"), COMPLETE("결제완료"), REFUSE("결제거부"), REFUND("환불");

    private final String description;

    PayStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
