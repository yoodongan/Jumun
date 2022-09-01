package com.mihak.jumun.cart.dto;

import com.mihak.jumun.entity.MenuOption;
import lombok.Getter;

import java.util.List;

@Getter
public class CartForm {

    private String name;
    private String imgUrl;
    private String description;
    private int price;

    private List<MenuOption> options;
    private List<Long> checkOptionIds;
    private int count;
}