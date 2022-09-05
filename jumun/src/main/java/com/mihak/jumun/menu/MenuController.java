package com.mihak.jumun.menu;

import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.menu.form.MenuForm;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final CategoryService categoryService;
    private final StoreService storeService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menu")
    public String menuForm(@PathVariable String storeSN, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menuForm", new MenuForm());
        return "menu/create_menu";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/menu")
    public String create(@PathVariable("storeSN") String storeSN, @Valid MenuForm menuForm, BindingResult result) {
        // 메뉴명 Null 값, 가격 Null 값 예외 체크
        if (result.hasErrors()) {
            return "menu/create_menu";
        }
        // 메뉴명 중복 체크.
        Optional<Menu> oMenu = menuService.findByName(menuForm.getName());
        if (oMenu.isPresent()) {
            result.rejectValue("name", "duplicatedMenu", "이미 똑같은 메뉴가 있습니다.");
            return "menu/create_menu";
        }
        Store store = storeService.findBySerialNumber(storeSN);
        menuForm.setStore(store);
        menuService.saveMenu(menuForm);
        return "redirect:/" + store.getSerialNumber() + "/admin/store/menuList";
    }

    // 관리자 메뉴 화면 보여주기 .
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menuList")
    public String menuList(@PathVariable String storeSN, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findAll();
        model.addAttribute("menuList", menuList);
        model.addAttribute("storeSN", storeSN);

        return "menu/menu_list";
    }

    /* 메뉴 수정 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menu/modify/{menuId}")
    public String modifyMenuForm(@PathVariable String storeSN, @PathVariable Long menuId, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        Menu findMenu = menuService.findById(menuId);

        MenuForm menuForm = new MenuForm();
        Category category = findMenu.getCategory();

        menuForm.setMenuInfo(category.getId(), findMenu.getName(), findMenu.getPrice(), findMenu.getImgUrl(), findMenu.getDescription(), findMenu.getStore());

        model.addAttribute("menuForm", menuForm);
        return "menu/modify_menu";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/menu/modify/{menuId}")
    public String modifyMenu(@PathVariable("storeSN") String storeSN, @PathVariable Long menuId, @Valid MenuForm menuForm, BindingResult result) {
        // 메뉴명 Null 값, 가격 Null 값 예외 체크
        if (result.hasErrors()) {
            return "menu/modify_menu";
        }
        // 메뉴명 중복 체크.
        Optional<Menu> oMenu = menuService.findByName(menuForm.getName());
        if (oMenu.isPresent() && oMenu.get().getId() != menuId) {
            result.rejectValue("name", "duplicatedMenu", "똑같은 메뉴명이 이미 존재합니다!");
            return "menu/modify_menu";
        }
        Store store = storeService.findBySerialNumber(storeSN);
        menuForm.setStore(store);
        menuService.changeMenu(menuId, menuForm);
        return "redirect:/" + store.getSerialNumber() + "/admin/store/menuList";

    }

    /* 메뉴 삭제 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menu/delete/{menuId}")
    public String deleteMenu(@PathVariable("storeSN") String storeSN, @PathVariable Long menuId) {
        Menu menu = menuService.findById(menuId);
        Store store = menu.getStore();
        menuService.remove(menu);

        return "redirect:/" + store.getSerialNumber() + "/admin/store/menuList";
    }

}