package com.mihak.jumun.customer;

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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerMenuService {
    private final OptionService optionService;
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
                .optionGroups(optionGroupService.getOptionGroupByMenu(menu))
//                .optionAndOptionGroupList()
                /*체크옵션은 GetMapping에서 건드는게 아닌거 같음*/
//                .checkOptions(optionService.getOptionsByCart(cart))
                .build();

        return customerMenuForm;
    }

}





//    /*해당 가게의 메뉴만*/
//    Menu menuDetail = menuService.findById(id);
//        model.addAttribute("menuDetail",menuDetail);

//    /*해당 가게의 메뉴옵션그룹 뽑기*/
////        List<Option> menuOptionList = optionService.findAll();
//    List<OptionGroup> optionGroups = optionGroupService.getOptionGroupByMenu(menuDetail);
//    model.addAttribute("optionGroups", optionGroups);
