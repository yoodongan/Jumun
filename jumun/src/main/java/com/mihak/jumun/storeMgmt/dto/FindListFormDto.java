package com.mihak.jumun.storeMgmt.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
public class FindListFormDto {
    private LocalDateTime orderedAt;
    private long totalPrice;
//    private String userNickName;

    public FindListFormDto(LocalDateTime orderedAt, long totalPrice) {
        this.orderedAt =  orderedAt;
        this.totalPrice = totalPrice;
    }

    public String getChangeOrderedAt(){
        String orderedAtDaily = orderedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return orderedAtDaily;
    }
    public long getTotalPrice(){
        return totalPrice;
    }

}
