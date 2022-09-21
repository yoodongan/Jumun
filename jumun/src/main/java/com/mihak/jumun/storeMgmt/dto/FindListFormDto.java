package com.mihak.jumun.storeMgmt.dto;

import com.mihak.jumun.entity.PayType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindListFormDto {
    private LocalDateTime orderedAt;
    private long totalPrice;
//    private String userNickName;

//    public FindListFormDto(String date, String sum) {
//        this.date = date;
//        this.sum = sum;
//    }

    public LocalDateTime getOrderedAt(){
        return orderedAt;
    }
    public long getTotalPrice(){
        return totalPrice;
    }

}
