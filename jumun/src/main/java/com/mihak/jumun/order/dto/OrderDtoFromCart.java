package com.mihak.jumun.order.dto;

import com.mihak.jumun.entity.OrderType;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoFromCart {
    private String userNickName;
    private OrderType orderType;
    private int totalPrice;
    private String storeSerialNumber;
}
