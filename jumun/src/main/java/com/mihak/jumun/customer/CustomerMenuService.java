package com.mihak.jumun.customer;

import com.mihak.jumun.cart.CartRepository;
import com.mihak.jumun.cart.dto.CartFormDto;
import com.mihak.jumun.customer.form.CustomerMenuForm;
import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.OptionGroup;
import com.mihak.jumun.exception.CartNotFoundException;
import com.mihak.jumun.menu.MenuService;
import com.mihak.jumun.option.OptionService;
import com.mihak.jumun.optionGroup.OptionGroupService;
import com.mihak.jumun.optionGroup.form.OptionGroupFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerMenuService {
    private final OptionService optionService;
    private final OptionGroupService optionGroupService;
    private final MenuService menuService;
    private final CartRepository cartRepository;

    public CustomerMenuForm getMenuFormById(Long id) {
        Menu menu = menuService.findById(id);
        /**/
        CustomerMenuForm customerMenuForm = CustomerMenuForm.builder()
                .category(menu.getCategory().getName())
                .name(menu.getName())
                .imgUrl(menu.getImgUrl())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .optionGroups(optionGroupService.getOptionGroupByMenu(menu))
//                .checkOptions(customer)
                .build();

        return customerMenuForm;
    }

    public CartFormDto addToForm(CustomerMenuForm customerMenuForm, Menu menu) {
        CartFormDto cartFormDto = CartFormDto.builder()
                    .name(customerMenuForm.getName())
                    .imgUrl(customerMenuForm.getImgUrl())
                    .description(customerMenuForm.getDescription())
                    .price(customerMenuForm.getPrice())
                    .optionGroups(optionGroupService.getOptionGroupByMenu(menu))
                .checkOptions(customerMenuForm.getCheckOptions())
                .build();

        return cartFormDto;
        }
    }
