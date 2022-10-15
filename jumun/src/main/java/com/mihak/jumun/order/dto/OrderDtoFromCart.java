package com.mihak.jumun.order.dto;

import com.mihak.jumun.entity.OrderType;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoFromCart implements Serializable {
    private String userNickname;
    private OrderType orderType;
    private int totalPrice;
    private String storeSerialNumber;
}
