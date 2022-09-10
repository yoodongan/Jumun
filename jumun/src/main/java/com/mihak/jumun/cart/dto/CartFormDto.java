package com.mihak.jumun.cart.dto;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.OptionGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
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
// 메뉴상세보기 - 페이지
// 메뉴 - name, imgurl, description, price, optionGroups
// optionGroups
// option1 2 3

//