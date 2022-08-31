package com.mihak.jumun.category;

import com.mihak.jumun.category.form.CategoryForm;
import com.mihak.jumun.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void create(CategoryForm categoryForm) {
        Category newCate = new Category();
        newCate.setName(categoryForm.getName());
        categoryRepository.save(newCate);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void modify(Category cate , String name){
        cate.setName(name);
        categoryRepository.save(cate);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public void remove(Category delCate) {
        categoryRepository.delete(delCate);
    }
}
