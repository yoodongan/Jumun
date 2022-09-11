package com.mihak.jumun.customer;

import com.mihak.jumun.customer.form.CustomerMenuForm;
import com.mihak.jumun.entity.*;
import com.mihak.jumun.menu.MenuService;
import com.mihak.jumun.optionAndOptionGroup.OptionAndOptionGroupService;
import com.mihak.jumun.store.StoreService;
import com.mihak.jumun.storeCategory.SCService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerMenuController {
    private final MenuService menuService;
    private final StoreService storeService;
    private final SCService scService;
    private final CustomerMenuService customerMenuService;
    private final OptionAndOptionGroupService optionAndOptionGroupService;


    @GetMapping("/{storeSN}/menu")
    public String menuView(@PathVariable("storeSN") String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findAllByStore(storeSN);
        model.addAttribute("menuList" , menuList);
        model.addAttribute("storeSN", storeSN);

        return "customer/customer_menu";
    }

    @GetMapping("/{storeSN}/menu/{id}/option")
    public String menuDetail(@PathVariable("storeSN") String storeSN, @PathVariable Long id, Model model){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("storeSN", storeSN);
        model.addAttribute("id", id);

        CustomerMenuForm customerMenuForm = customerMenuService.getMenuFormById(id);
        model.addAttribute("customerMenuForm", customerMenuForm);

//        List<OptionAndOptionGroup> optionsInOptionGroup = optionAndOptionGroupService.findOptionsByOptionGroupId();
//        model.addAttribute("optionsInOptionGroup", optionsInOptionGroup);
        /*해당 메뉴의 카테고리만 가져오고 싶은데 어떻게 가져오지???*/
//        /*해당 가게의 카테고리만*/
//        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
//        model.addAttribute("categoryList", categoryList);

        return "customer/customer_menu_detail";
    }
}

