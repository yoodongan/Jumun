package com.mihak.jumun.category;

import com.mihak.jumun.category.form.CategoryForm;
import com.mihak.jumun.entity.Owner;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.entity.StoreAndCategory;
import com.mihak.jumun.store.StoreService;
import com.mihak.jumun.storeCategory.SCService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.mihak.jumun.entity.Category;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final StoreService storeService;
    private final CategoryService categoryService;
    private final SCService scService;


    @GetMapping("{storeSN}/admin/store/category")
    public String create(Model model, @ModelAttribute CategoryForm categoryForm , @PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);

        model.addAttribute("store",store);
        return "category/create_cate";
    }

    @PostMapping("/{storeSN}/admin/store/category")
    public String createCate(@Valid CategoryForm categoryForm, BindingResult bindingResult, @PathVariable String storeSN) {

        if(bindingResult.hasErrors()){
            return "category/create_cate";
        }

        Owner owner = getOwner(storeSN);
        Optional<Category> cate = categoryService.findByNameAndOwner(categoryForm.getName(),owner);

        if(cate.isPresent()){
            //cateform은 자동으로 모델에 담김
                bindingResult.rejectValue("name", "duplicate", "중복됨");
                return "category/create_cate";
        }
        categoryService.create(categoryForm,owner);
        Optional<Category> newCategory = categoryService.findByNameAndOwner(categoryForm.getName(),owner);
        scService.save(storeSN , newCategory.get().getId());

        return "redirect:/%s/admin/store/categoryList".formatted(storeSN);
    }


    @GetMapping("/{storeSN}/admin/store/categoryList")
    public String showCate(Model model,@PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> scList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("store", store);
        model.addAttribute("list" , scList);
        model.addAttribute("storeSN",storeSN);
        return "category/cate_list";
    }


    @GetMapping("/{storeSN}/admin/store/category/modify/{id}")
    public String modify(CategoryForm categoryForm, @PathVariable Long id,@PathVariable String storeSN){
        Category cate = categoryService.findById(id);

        categoryForm.setName(cate.getName());
        return "category/cate_modify";
    }

    @PostMapping("/{storeSN}/admin/store/category/modify/{id}")
    public String modify(@PathVariable String storeSN, @Valid CategoryForm categoryForm,BindingResult bindingResult ,Model model , @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "category/cate_modify";
        }
        Owner owner = getOwner(storeSN);
        Category beforeModifyCate = categoryService.findById(id);
        Optional<Category> wantModifyCate = categoryService.findByNameAndOwner(categoryForm.getName(),owner);

        if(wantModifyCate.isPresent()){
            bindingResult.rejectValue("name", "duplicate", "중복임");
            return "category/cate_modify";
        }
        categoryService.modify(beforeModifyCate, categoryForm.getName());
        return "redirect:/%s/admin/store/categoryList".formatted(storeSN);


    }

    @GetMapping("/{storeSN}/admin/store/category/delete/{id}")
    public String delete(@PathVariable("id") Long id, @PathVariable String storeSN) {
        //해당 스토어와 삭제하고 싶은 카테고리를 가져온다.
        Store store = storeService.findBySerialNumber(storeSN);
        Category delCate = categoryService.findById(id);

        scService.remove(scService.findByStoreAndCategory(store,delCate).get());

        categoryService.remove(delCate.getId());
        return "redirect:/%s/admin/store/categoryList".formatted(storeSN);
    }

    private Owner getOwner(String storeSN) {
        Store store = storeService.findBySerialNumber(storeSN);
        Owner owner = store.getOwner();
        return owner;
    }
}
