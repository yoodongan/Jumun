package com.mihak.jumun.menu;

import com.mihak.jumun.category.CategoryRepository;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Long save(Long storeId, int categoryId, Menu menu) {
//        validateDuplicatedMenu(storeId, menu);
        Optional<Store> store = storeRepository.findById(storeId);
        Optional<Category> category = categoryRepository.findById(categoryId);
        Menu newMenu = Menu.createMenu(menu.getName(), menu.getPrice(), menu.getDescription(), menu.getImage(), menu.getCategory(), menu.getStore());
        menuRepository.save(newMenu);
        return menu.getId();
    }

//    private void validateDuplicatedMenu(Long storeId, Menu menu) {   // 해당 음식점 안에서의 메뉴 중복 검사. (음식점이 다르다면 메뉴 중복 가능)
//        Optional<Store> store = storeRepository.findById(storeId);
//
//        List<Menu> findMenus = store.get().getMenus();
//        if (findMenus.contains(menu)) {
//            throw new IllegalStateException("메뉴가 이미 존재합니다!");
//        }
//    }


}
