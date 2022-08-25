package com.mihak.jumun.category;

import com.mihak.jumun.category.form.CategoryForm;
import com.mihak.jumun.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/create")
    public String create(CategoryForm categoryForm){
        return "create_cate";
    }

    @PostMapping("/create")
    public String createCate(Model model , @Valid CategoryForm categoryForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "create_cate";
        }
        categoryService.create(categoryForm);
        return "redirect:/category/list";
    }

    @GetMapping("/list")
    public String showCate(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("list" , categoryList);

        return "cate_list";
    }
    @GetMapping("/detail/{id}")
    public String showDetail(Model model , @PathVariable int id){
        Category cate = categoryService.findById(id);

        model.addAttribute("cate",cate);
        return "cate_detail";
    }
}