package com.mihak.jumun.storeCategory;

import com.mihak.jumun.category.CategoryRepository;
import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.entity.StoreAndCategory;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SCService {

    private final StoreService storeService;
    private final CategoryService categoryService;

    private final CategoryRepository categoryRepository;
    private final SCRepository scRepository;

    public void save(String storeSN, String name) {
        StoreAndCategory sc = new StoreAndCategory();
        Store store = storeService.findBySerialNumber(storeSN);
        Category category = categoryService.findByName(name).get();
        sc.setStore(store);
        sc.setCategory(category);
        scRepository.save(sc);
    }

    public List<Category> findAllbyStoreId(Long id) {
        List<StoreAndCategory> li = scRepository.findAll();
        for(StoreAndCategory d : li){
            System.out.println(d);
        }
        List<Category> cList = new ArrayList<>();
        for (StoreAndCategory sc : li) {
            if (sc.getStore().getId() == id) {
                cList.add(sc.getCategory());
            }
        }
        return cList;
    }

    public StoreAndCategory findByCategory(Category delCate) {
        return scRepository.findByCategory(delCate);
    }

    public void remove(StoreAndCategory sc) {
        scRepository.delete(sc);
    }

    public Optional<StoreAndCategory> findByStoreAndCategory(Store store,Category cate) {
        return scRepository.findByStoreAndCategory(store , cate);
    }

    public List<StoreAndCategory> findAllByCategory(Long id) {
        Category category = categoryRepository.findById(id).get();
        List<StoreAndCategory> li = scRepository.findAllByCategory(category);
        return li;
    }

    public void modify(String storeSN, Category order, Category newer) {
        Store store = storeService.findBySerialNumber(storeSN);
        StoreAndCategory sc = scRepository.findByStoreAndCategory(store,order).get();
        sc.setCategory(newer);
        scRepository.save(sc);
    }
}
