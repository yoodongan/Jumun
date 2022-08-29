package com.mihak.jumun.storeCategory;

import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.entity.StoreCategory;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SCService {

    private final StoreService storeService;
    private final CategoryService categoryService;
    private final SCRepository scRepository;

    public void save(String storeSN, String name) {
        StoreCategory sc = new StoreCategory();
        Store store = storeService.findBySerialNumber(storeSN);
        Category category = categoryService.findByName(name).get();
        sc.setStore(store);
        sc.setCategory(category);
        scRepository.save(sc);
    }

    public List<Category> findAllbyStoreId(Long id) {
        List<StoreCategory> li = scRepository.findAll();
        for(StoreCategory d : li){
            System.out.println(d);
        }
        List<Category> cList = new ArrayList<>();
        for (StoreCategory sc : li) {
            if (sc.getStore().getId() == id) {
                cList.add(sc.getCategory());
            }
        }
        return cList;
    }

    public StoreCategory findByCategory(Category delCate) {
        return scRepository.findByCategory(delCate);
    }

    public void remove(StoreCategory sc) {
        scRepository.delete(sc);
    }
}
