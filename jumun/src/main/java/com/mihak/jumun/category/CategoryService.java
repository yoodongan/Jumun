package com.mihak.jumun.category;

import com.mihak.jumun.category.dto.CategoryFormDto;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Owner;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;

    public void save(CategoryFormDto categoryFormDto, Owner owner) {
        Category newCate = new Category();
        newCate.setName(categoryFormDto.getName());
        newCate.setOwner(owner);
        categoryRepository.save(newCate);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new RuntimeException("해당 객체 없음"));
    }

    public void modifyCategory(Category cate , String name){
        cate.setName(name);
        categoryRepository.save(cate);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public void deleteById(Long id) {
        List<Menu> menuList = menuRepository.findByCategoryId(id);
        for (Menu menu : menuList) {
            menu.setCategory(null);
        }
        categoryRepository.deleteById(id);
    }

    public Optional<Category> findByNameAndOwner(String name , Owner owner) {
        return categoryRepository.findByNameAndOwner(name,owner);
    }

    public Owner getOwnerBySerialNumber(String storeSN) {
        Store store = categoryRepository.findBySerialNumber(storeSN);
        Owner owner = store.getOwner();
        return owner;
    }
}
