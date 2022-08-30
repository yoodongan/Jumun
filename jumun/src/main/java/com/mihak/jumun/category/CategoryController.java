package com.mihak.jumun.category;

import com.mihak.jumun.category.form.CategoryForm;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.entity.StoreCategory;
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


    @GetMapping("{storeSN}/category/create")
    public String create(Model model, @ModelAttribute CategoryForm categoryForm , @PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);

        model.addAttribute("store",store);
        return "category/create_cate";
    }

    @PostMapping("/{storeSN}/category/create")
    public String createCate(Model model , @Valid CategoryForm categoryForm, BindingResult bindingResult, @PathVariable String storeSN) {
        Optional<Category> cate = categoryService.findByName(categoryForm.getName());
        if(bindingResult.hasErrors()){
            return "category/create_cate";
        }else if(cate.isPresent()){
            bindingResult.rejectValue("name", "duplicate",
                    "중복임");
            return "category/create_cate";
        }
        categoryService.create(categoryForm);
        Optional<Category> cate2 = categoryService.findByName(categoryForm.getName());
        scService.save(storeSN , cate2.get().getName());
        System.out.println(storeSN);
        return "redirect:/%s/category/list".formatted(storeSN);
    }

    @GetMapping("/{storeSN}/category/list")
    public String showCate(Model model,@PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> scList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("list" , scList);
        model.addAttribute("storeSN",storeSN);
        return "/category/cate_list";
    }
    @GetMapping("/{storeSN}/category/detail/{id}")
    public String showDetail(Model model , @PathVariable int id, HttpServletResponse res , @PathVariable String storeSN) throws Exception {
        Optional<Category> cate = categoryService.findById(id);
        if(!(cate.isPresent())) {
            return "redirect:/%s/category/list".formatted(storeSN);
        }
        model.addAttribute("storeSN",storeSN);
        model.addAttribute("cate",cate.get());
        return "category/cate_detail";
    }

    @GetMapping("/{storeSN}/category/modify/{id}")
    public String modify(CategoryForm categoryForm,Model model , @PathVariable int id,@PathVariable String storeSN){
        Category cate = categoryService.findById(id).get();

        categoryForm.setName(cate.getName());
        return "category/cate_modify";
    }

    @PostMapping("/{storeSN}/category/modify/{id}")
    public String modify(@PathVariable String storeSN, @Valid CategoryForm categoryForm,BindingResult bindingResult ,Model model , @PathVariable int id){
        Category newcate = categoryService.findById(id).get();

        Optional<Category> cate = categoryService.findByName(categoryForm.getName());
        if(bindingResult.hasErrors()){
            return "category/cate_modify";
        }else if(cate.isPresent()){
            bindingResult.rejectValue("name", "duplicate",
                    "중복임");
            return "category/cate_modify";
        }

        categoryService.modify(newcate, categoryForm.getName());
        return "redirect:/%s/category/list".formatted(storeSN);
    }

    @GetMapping("/{storeSN}/category/delete/{id}")
    public String delete( @PathVariable("id") int id, @PathVariable String storeSN) {

        Category delCate = categoryService.findById(id).get();
        StoreCategory sc = scService.findByCategory(delCate);
        scService.remove(sc);
        categoryService.remove(delCate);

        return "redirect:/%s/category/list".formatted(storeSN);
    }



}
