package com.mihak.jumun.category;

import com.mihak.jumun.category.form.CategoryForm;
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
    public String createCate(Model model , @Valid CategoryForm categoryForm, BindingResult bindingResult, @PathVariable String storeSN) {

        if(bindingResult.hasErrors()){
            return "category/create_cate";
        }

        Optional<Category> cate = categoryService.findByName(categoryForm.getName());
        Store store = storeService.findBySerialNumber(storeSN);


        if(cate.isPresent()){
            Optional<StoreAndCategory> sc = scService.findByStoreAndCategory(store ,cate.get());
            if(sc.isPresent()){
                bindingResult.rejectValue("name", "duplicate",
                        "중복임");
                return "category/create_cate";
            }else{
                scService.save(storeSN, cate.get().getName());
                return "redirect:/%s/admin/store/categoryList".formatted(storeSN);
            }
        }else{
            categoryService.create(categoryForm);
            Optional<Category> cate2 = categoryService.findByName(categoryForm.getName());
            scService.save(storeSN , cate2.get().getName());
        }
        return "redirect:/%s/admin/store/categoryList".formatted(storeSN);
    }


    @GetMapping("/{storeSN}/admin/store/categoryList")
    public String showCate(Model model,@PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> scList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("store", store);
        model.addAttribute("list" , scList);
        model.addAttribute("storeSN",storeSN);
        return "/category/cate_list";
    }
//    @GetMapping("/{storeSN}/admin/store/categoryDetail/{id}")
//    public String showDetail(Model model , @PathVariable Long id, HttpServletResponse res , @PathVariable String storeSN) throws Exception {
//        Optional<Category> cate = categoryService.findById(id);
//        if(!(cate.isPresent())) {
//            return "redirect:/%s/category/list".formatted(storeSN);
//        }
//        model.addAttribute("storeSN",storeSN);
//        model.addAttribute("cate",cate.get());
//        return "category/cate_detail";
//    }

    @GetMapping("/{storeSN}/admin/store/category/modify/{id}")
    public String modify(CategoryForm categoryForm,Model model , @PathVariable Long id,@PathVariable String storeSN){
        Category cate = categoryService.findById(id).get();

        categoryForm.setName(cate.getName());
        return "category/cate_modify";
    }

    @PostMapping("/{storeSN}/admin/store/category/modify/{id}")
    public String modify(@PathVariable String storeSN, @Valid CategoryForm categoryForm,BindingResult bindingResult ,Model model , @PathVariable Long id){
        Store store = storeService.findBySerialNumber(storeSN);
        if(bindingResult.hasErrors()){
            return "category/cate_modify";
        }

        Optional<Category> beforeModifyCate = categoryService.findById(id);
        Optional<Category> wantModifyCate = categoryService.findByName(categoryForm.getName());


        if(wantModifyCate.isPresent()){
            Optional<StoreAndCategory> sc = scService.findByStoreAndCategory(store,wantModifyCate.get());
            if(sc.isPresent()){
                bindingResult.rejectValue("name", "duplicate",
                        "중복임");

                return "category/cate_modify";
            }else {
                scService.modify(storeSN,beforeModifyCate.get(),wantModifyCate.get());
                long count = scService.findAllByCategory(id).size();
                if(count == 0){
                    categoryService.remove(categoryService.findById(id).get());
                }
                return "redirect:/%s/admin/store/categoryList".formatted(storeSN);
            }

        }else{
            createCate(model,categoryForm,bindingResult,storeSN);
            scService.remove(scService.findByStoreAndCategory(store,beforeModifyCate.get()).get());
        }
        long count = scService.findAllByCategory(id).size();
        if(count == 0){
            categoryService.remove(categoryService.findById(id).get());
        }

        return "redirect:/%s/admin/store/categoryList".formatted(storeSN);
    }

    @GetMapping("/{storeSN}/admin/store/category/delete/{id}")
    public String delete(@PathVariable("id") Long id, @PathVariable String storeSN) {
        //해당 스토어와 삭제하고 싶은 카테고리를 가져온다.
        Store store = storeService.findBySerialNumber(storeSN);
        Category delCate = categoryService.findById(id).get();

        //StoreAndCategory 테이블에 해당 카테고리를 참조하는 스토어 갯수를 가져온다.
        long count = scService.findAllByCategory(id).size();
        scService.remove(scService.findByStoreAndCategory(store,delCate).get());

        if(count == 1){
            categoryService.remove(delCate);
        }
        return "redirect:/%s/admin/store/categoryList".formatted(storeSN);
    }
}
