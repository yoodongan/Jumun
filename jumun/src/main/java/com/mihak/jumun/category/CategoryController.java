package com.mihak.jumun.category;

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

    private final CategoryService categoryService;

    @GetMapping("/category/create")
    public String create(@ModelAttribute CategoryForm categoryForm){
        return "category/create_cate";
    }

    @PostMapping("/category/create")
    public String createCate(Model model , @Valid CategoryForm categoryForm, BindingResult bindingResult) {
        Optional<Category> cate = categoryService.findByName(categoryForm.getName());
        if(bindingResult.hasErrors()){
            return "category/create_cate";
        }else if(cate.isPresent()){
            bindingResult.rejectValue("name", "duplicate",
                    "중복임");
            return "category/create_cate";
        }

        categoryService.create(categoryForm);
        return "redirect:/category/list";
    }
    @GetMapping("/category/list")
    public String showCate(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("list" , categoryList);

        return "/category/cate_list";
    }
    @GetMapping("/category/detail/{id}")
    public String showDetail(Model model , @PathVariable int id, HttpServletResponse res) throws Exception {
        Optional<Category> cate = categoryService.findById(id);
        if(!(cate.isPresent())) {
            return "redirect:/category/list";
        }
        model.addAttribute("cate",cate.get());
        return "category/cate_detail";
    }

    @GetMapping("/category/modify/{id}")
    public String modify(CategoryForm categoryForm,Model model , @PathVariable int id){
        Category cate = categoryService.findById(id).get();

        categoryForm.setName(cate.getName());
        return "category/cate_modify";
    }

    @PostMapping("/category/modify/{id}")
    public String modify(@Valid CategoryForm categoryForm,BindingResult bindingResult ,Model model , @PathVariable int id){
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
        return "redirect:/category/detail/%d".formatted(id);
    }

    @GetMapping("/category/delete/{id}")
    public String delete( @PathVariable("id") int id) {

        Category delCate = categoryService.findById(id).get();


        categoryService.remove(delCate);

        return "redirect:/category/list";
    }


}

