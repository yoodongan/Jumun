package com.mihak.jumun.cart.dto;

import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CartDto {
    private Long cartId;
    private Menu menu;
    private int count;
    private List<Option> options;
}
