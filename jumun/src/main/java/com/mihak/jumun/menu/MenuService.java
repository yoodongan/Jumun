package com.mihak.jumun.menu;

import com.mihak.jumun.category.CategoryRepository;
import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.*;
import com.mihak.jumun.exception.MenuNotFoundException;
import com.mihak.jumun.menu.form.MenuForm;
import com.mihak.jumun.store.StoreService;
import com.mihak.jumun.storeCategory.SCService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final StoreService storeService;
    private final SCService scService;


    public Long saveMenu(MenuForm menuForm) {
        Optional<Category> oCategory = categoryRepository.findById((menuForm.getCategoryId()));
        Category category = oCategory.get();
        Menu newMenu = Menu.createMenu(menuForm.getName(), menuForm.getPrice(), menuForm.getDescription(), menuForm.getImgUrl(), category, menuForm.getStore());
        menuRepository.save(newMenu);
        return newMenu.getId();
    }

    public Menu findById(Long id) {
        Optional<Menu> findMenu =  menuRepository.findById(id);
        if(!(findMenu.isPresent())) throw new MenuNotFoundException("수정할 메뉴가 없습니다!");
        return findMenu.get();
    }
    public List<Menu> findByCategoryId(Long id) {
        List<Menu> findMenu = menuRepository.findByCategoryId(id);
        return findMenu;
    }


    public void remove(Menu menu) {
        menuRepository.delete(menu);
    }


    public List<Menu> findAllByStore(String storeSN) {
        Store store = storeService.findBySerialNumber(storeSN);
        return menuRepository.findByStore(store);
    }

    public void changeMenu(Long menuId, MenuForm menuForm) {
        Optional<Menu> oMenu = menuRepository.findById(menuId);
        Optional<Category> oCategory = categoryRepository.findById((menuForm.getCategoryId()));
        Category category = oCategory.get();
        Menu menu = oMenu.get();
        menu.changeInfo(category, menuForm.getName(), menuForm.getPrice(), menuForm.getImgUrl(), menuForm.getDescription(), menuForm.getStore());
        menuRepository.save(menu);
    }

    // 메뉴에 옵션 추가 기능.
    public void addMenuOptions(Menu menu, MenuAndOptionGroup... menuAndOptionGroups) {
        Menu findMenu = menuRepository.findById(menu.getId()).get();
        for (MenuAndOptionGroup menuAndOptionGroup : menuAndOptionGroups) {
            findMenu.addMenuMenuOption(menuAndOptionGroup);
        }
    }

    public boolean isMenuDuplicated(String name, Store store) {
        Optional<Menu> menu = menuRepository.findByNameAndStore(name, store);
        if(menu.isPresent()) return true;  // 중복된 메뉴가 있다.
        else return false;

    }

    public boolean isMenuDuplicatedAndDifferentId(String name, Store store, Long menuId) {
        Optional<Menu> menu = menuRepository.findByNameAndIdNot(name, menuId);
        if(menu.isPresent() && isMenuDuplicated(name, store)) return true;
        else return false;
    }
}
