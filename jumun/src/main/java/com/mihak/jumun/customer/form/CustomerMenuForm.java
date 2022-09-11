package com.mihak.jumun.customer.form;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.OptionAndOptionGroup;
import com.mihak.jumun.entity.OptionGroup;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMenuForm {
    private String category;
    private String name;
    private String imgUrl;
    private String description;
    private int price;
    private List<OptionGroup> optionGroups;
//    private List<OptionAndOptionGroup> optionAndOptionGroupList;
    private List<Option> checkOptions;
    private int count;
    //카운트는 위아래 화살표로 디폴트 값 1로?
    //주문하기를 누르면 카트폼DTO로 넘어가야함.
}