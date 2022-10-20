package com.mihak.jumun.cart.dto;

import com.mihak.jumun.option.entity.Option;
import com.mihak.jumun.optionGroup.entity.OptionGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDto {

    private String name;
    private String imgUrl;
    private String description;
    private int price;

    private List<OptionGroup> optionGroups;
    private List<Option> checkOptions;
    private int count;
}