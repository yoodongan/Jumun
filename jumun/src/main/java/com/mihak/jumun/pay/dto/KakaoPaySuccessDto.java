package com.mihak.jumun.pay.dto;

import com.mihak.jumun.cart.dto.CartDto;
import com.mihak.jumun.entity.OrderStatus;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoPaySuccessDto {
    private String userNickName;
    private Long orderId;
    private List<CartDto> orderHistory;
    private OrderStatus orderStatus;
    private int orderTotalPrice;
    private String storeSN;
}
