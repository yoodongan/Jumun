package com.mihak.jumun.customer;

import com.mihak.jumun.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class CustomerMenuController {

    /*카테고리id로 URL분기, 선택된 카테고리 내 메뉴리스트 출력*/
//    @GetMapping("/{id}")
//    public String menuView(Model model, @PathVariable int id) {
//
//        /*카테고리 보여줄 부분*/
//        List<Category> categoryList = categoryService.findAll();
//        model.addAttribute("list", categoryList);
//        return "customer_menu";
//    }

    /*선택된 카테고리 내에 메뉴 리스트*/
//    @GetMapping("/category/detail/{id}")
//        public String showDetail(Model model , @PathVariable int id, HttpServletResponse res) throws Exception {
//            Optional<Category> cate = categoryService.findById(id);
//            if(!(cate.isPresent())) {
//                return "redirect:/category/list";
//            }
//            model.addAttribute("cate",cate.get());
//            return "category/cate_detail";
//        }
    @GetMapping("")
    public String menuView(Model model) {
        return "customer_menu";
    }
}

