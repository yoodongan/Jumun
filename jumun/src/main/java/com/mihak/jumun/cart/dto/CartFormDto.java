package com.mihak.jumun.cart.dto;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.OptionGroup;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartFormDto {

    private String name;
    private String imgUrl;
    private String description;
    private int price;

    private List<OptionGroup> optionGroups;

    private List<Option> checkOptions;
    private int count;
}