package com.mihak.jumun.cart.dto;

import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.MenuOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CartDto {
    private Long id;
    private Menu menu;
    private int count;
    private List<MenuOption> menuOptions;
}
