package com.mihak.jumun.menu;

import com.mihak.jumun.category.CategoryRepository;
import com.mihak.jumun.entity.*;
import com.mihak.jumun.exception.CategoryNotFoundException;
import com.mihak.jumun.exception.MenuNotFoundException;
import com.mihak.jumun.menu.dto.MenuFormDto;
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


    public Long save(MenuFormDto menuformDto) {
        Category category = null;
        if(menuformDto.getCategoryId() == null) {
            category = null;
        } else {
            Optional<Category> oCategory = categoryRepository.findById((menuformDto.getCategoryId()));
            category = oCategory.get();
        }
        Menu newMenu = Menu.createMenu(menuformDto.getName(), menuformDto.getPrice(), menuformDto.getDescription(), menuformDto.getImgUrl(), category, menuformDto.getStore());
        menuRepository.save(newMenu);
        return newMenu.getId();
    }

    public Menu findById(Long id) {
        Optional<Menu> findMenu =  menuRepository.findById(id);
        if(!(findMenu.isPresent())) throw new MenuNotFoundException("수정할 메뉴가 없습니다!");
        return findMenu.get();
    }
    public List<Menu> findAllByCategoryId(Long id) {
        List<Menu> findMenu = menuRepository.findByCategoryId(id);
        return findMenu;
    }

    public List<Menu> findAllByCategoryAndStore(Long categoryId, Store store) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException());

        return menuRepository.findByCategoryAndStore(category, store);
    }

    public void deleteByMenu(Menu menu) {
        menuRepository.delete(menu);
    }


    public List<Menu> findAllByStore(String storeSN) {
        Store store = storeService.findBySerialNumber(storeSN);
        return menuRepository.findByStore(store);
    }

    public void modify(Long menuId, MenuFormDto menuformDto) {
        Optional<Menu> oMenu = menuRepository.findById(menuId);
        Optional<Category> oCategory = categoryRepository.findById((menuformDto.getCategoryId()));
        Category category = oCategory.get();
        Menu menu = oMenu.get();
        menu.changeInfo(category, menuformDto.getName(), menuformDto.getPrice(), menuformDto.getImgUrl(), menuformDto.getDescription(), menuformDto.getStore());
        menuRepository.save(menu);
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
