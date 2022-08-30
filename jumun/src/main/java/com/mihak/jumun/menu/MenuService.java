package com.mihak.jumun.menu;

import com.mihak.jumun.category.CategoryRepository;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.menu.form.MenuForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public Long saveMenu(MenuForm menuForm) {
        Optional<Category> oCategory = categoryRepository.findById((menuForm.getCategoryId()));
        Category category = oCategory.get();
        Menu newMenu = Menu.createMenu(menuForm.getName(), menuForm.getPrice(), menuForm.getDescription(), menuForm.getImgUrl(), category, menuForm.getStore());
        menuRepository.save(newMenu);
        return newMenu.getId();
    }

    public Optional<Menu> findById(Long id) {
        return menuRepository.findById(id);
    }

    public Optional<Menu> findByName(String name) {
        return menuRepository.findByName(name);
    }

    public void remove(Menu menu) {
        menuRepository.delete(menu);
    }

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public void changeMenu(Long menuId, MenuForm menuForm) {
        Optional<Menu> oMenu = menuRepository.findById(menuId);
        Optional<Category> oCategory = categoryRepository.findById((menuForm.getCategoryId()));
        Category category = oCategory.get();
        Menu menu = oMenu.get();
        menu.changeInfo(category, menuForm.getName(), menuForm.getPrice(), menuForm.getImgUrl(), menuForm.getDescription(), menuForm.getStore());
        menuRepository.save(menu);
    }
}
