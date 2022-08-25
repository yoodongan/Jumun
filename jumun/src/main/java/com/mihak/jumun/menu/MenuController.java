package com.mihak.jumun.menu;

import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.menu.form.MenuForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/store")
public class MenuController {

    private final MenuService menuService;
    private final MenuRepository menuRepository;
    private final CategoryService categoryService;
    private final StoreRepository storeRepository;

    @GetMapping("/menu/{id}")
    public String menuForm(@PathVariable int id, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        MenuForm menuForm = new MenuForm();
        Optional<Store> oStore = storeRepository.findById((long)id);
        menuForm.setStore(oStore.get());
        model.addAttribute("menuForm", menuForm);
        return "menu/createMenuForm";
    }

    @PostMapping("/menu/{id}")
    public String create(@PathVariable("id") int id, @Valid MenuForm menuForm, BindingResult result) {
        if (result.hasErrors()) {
            return "menu/createMenuForm";
        }
        menuForm.setStore(storeRepository.findById((long)id).get());

        Category category = categoryService.findById(menuForm.getCategoryId());
        Menu menu = Menu.createMenu(menuForm.getName(), menuForm.getPrice(), menuForm.getDescription(), menuForm.getImg(), category, menuForm.getStore());
        menuService.save(1L, 1, menu);  // 임시로 일단 1 넣어주기.
        return "redirect:/admin/store/menu/1";
    }



}