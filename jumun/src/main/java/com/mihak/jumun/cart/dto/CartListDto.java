package com.mihak.jumun.cart.dto;

import com.mihak.jumun.entity.OrderType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartListDto {
    private List<CartDto> cartDtos;
    private OrderType orderType;
    private int totalPrice;
}
