package com.mihak.jumun.menu;

import com.mihak.jumun.category.CategoryRepository;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.menu.form.MenuForm;
import com.mihak.jumun.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public Long saveMenu(MenuForm menuForm) {
        Optional<Category> oCategory = categoryRepository.findById(menuForm.getCategoryId());
        Category category = oCategory.get();
        Menu newMenu = Menu.createMenu(menuForm.getName(), menuForm.getPrice(), menuForm.getDescription(), menuForm.getImg(), category, menuForm.getStore());
        menuRepository.save(newMenu);
        return newMenu.getId();
    }

    public Optional<Menu> findById(Integer id) {
        return menuRepository.findById(id);
    }

    public Optional<Menu> findByName(String name) {
        return menuRepository.findByName(name);
    }

}
