package com.mihak.jumun.customer;

import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.*;
import com.mihak.jumun.menu.MenuService;
import com.mihak.jumun.menuAndOption.MenuOptionService;
import com.mihak.jumun.store.StoreService;
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
    private final CategoryService categoryService;
    private final MenuOptionService menuOptionService;


    @GetMapping("/{storeSN}/menu")
    public String menuView(@PathVariable String storeSN, Model model) {

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findAll();
        model.addAttribute("menuList" , menuList);
        model.addAttribute("storeSN", storeSN);

        return "customer/customer_menu";
    }

    @GetMapping("/{storeSN}/menu/detail/{id}")
    public String menuDetail(@PathVariable String storeSN, @PathVariable Long id, Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("storeSN", storeSN);
        Menu menuDetail = menuService.findById(id);
        model.addAttribute("menuDetail",menuDetail);
        //MenuOption menuOption = menuOptionService.findByMenuId(id);
        /*일단 메뉴옵션 있는거 리스트로 뽑아볼까*/
        List<MenuOption> menuOptionList = menuOptionService.findAll();
        model.addAttribute("menuOption", menuOptionList);
        return "customer/customer_menu_detail";
    }
}

