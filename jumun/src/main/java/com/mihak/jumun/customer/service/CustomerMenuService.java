package com.mihak.jumun.customer.service;

import com.mihak.jumun.customer.dto.MenuDetailFormDto;
import com.mihak.jumun.menu.entity.Menu;
import com.mihak.jumun.menu.service.MenuService;
import com.mihak.jumun.optionGroup.service.OptionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomerMenuService {
    private final OptionGroupService optionGroupService;
    private final MenuService menuService;

    public MenuDetailFormDto getMenuFormById(Long id) {
        Menu menu = menuService.findById(id);
        MenuDetailFormDto menuDetailFormDto = MenuDetailFormDto.builder()
                .category(menu.getCategory().getName())
                .name(menu.getName())
                .imgUrl(menu.getImgUrl())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .optionGroups(optionGroupService.getOptionGroupsByMenu(menu))
                .checkOptions(new ArrayList<>())
                .build();

        return menuDetailFormDto;
    }

}
