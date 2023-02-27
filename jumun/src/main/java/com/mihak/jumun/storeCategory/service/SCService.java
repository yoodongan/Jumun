package com.mihak.jumun.storeCategory.service;

import com.mihak.jumun.category.entity.Category;
import com.mihak.jumun.category.service.CategoryService;
import com.mihak.jumun.store.entity.Store;
import com.mihak.jumun.store.service.StoreService;
import com.mihak.jumun.storeCategory.entity.StoreAndCategory;
import com.mihak.jumun.storeCategory.repository.SCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SCService {

    private final StoreService storeService;
    private final CategoryService categoryService;
    private final SCRepository scRepository;



    public void save(String storeSN, Long id) {
        StoreAndCategory sc = new StoreAndCategory();
        Store store = storeService.findBySerialNumber(storeSN);
        Category category = categoryService.findById(id);
        sc.setStore(store);
        sc.setCategory(category);
        scRepository.save(sc);
    }

    public List<Category> findAllbyStoreId(Long id) {
        List<StoreAndCategory> li = scRepository.findAll();

        List<Category> cList = li.stream()
                .filter(sc -> sc.getStore().getId() == id)
                .map(StoreAndCategory::getCategory)
                .collect(Collectors.toList());
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
        Category category = categoryService.findById(id);
        List<StoreAndCategory> li = scRepository.findAllByCategory(category);
        return li;
    }

    public void modify(String storeSN, Category order, Category newer) {
        Store store = storeService.findBySerialNumber(storeSN);
        StoreAndCategory sc = scRepository.findByStoreAndCategory(store,order).orElseThrow(() -> new RuntimeException("에러"));
        sc.setCategory(newer);
        scRepository.save(sc);
    }
}
