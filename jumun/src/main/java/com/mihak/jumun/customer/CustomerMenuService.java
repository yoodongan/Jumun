package com.mihak.jumun.customer;

import com.mihak.jumun.cart.dto.CartFormDto;
import com.mihak.jumun.customer.form.CustomerMenuForm;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.menu.MenuService;
import com.mihak.jumun.optionGroup.OptionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomerMenuService {
    private final OptionGroupService optionGroupService;
    private final MenuService menuService;

    public CustomerMenuForm getMenuFormById(Long id) {
        Menu menu = menuService.findById(id);
        CustomerMenuForm customerMenuForm = CustomerMenuForm.builder()
                .category(menu.getCategory().getName())
                .name(menu.getName())
                .imgUrl(menu.getImgUrl())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .optionGroups(optionGroupService.getOptionGroupsByMenu(menu))
                .checkOptions(new ArrayList<>())
                .build();

        return customerMenuForm;
    }

    public CartFormDto addToForm(CustomerMenuForm customerMenuForm, Menu menu) {
        CartFormDto cartFormDto = CartFormDto.builder()
                    .name(customerMenuForm.getName())
                    .imgUrl(customerMenuForm.getImgUrl())
                    .description(customerMenuForm.getDescription())
                    .price(customerMenuForm.getPrice())
                    .optionGroups(optionGroupService.getOptionGroupsByMenu(menu))
                .checkOptions(customerMenuForm.getCheckOptions())
                .build();

        return cartFormDto;
        }
    }
