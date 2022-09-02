package com.mihak.jumun.customer;

<<<<<<< HEAD
import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.menu.MenuService;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
=======
import com.mihak.jumun.entity.Category;
>>>>>>> 4ace27b (Feat : git pull하기(#11))
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerMenuController {
    private final MenuService menuService;
    private final StoreService storeService;
    private final CategoryService categoryService;

<<<<<<< HEAD
    /*이 부분은 동안님 코드 참고해야겠다.*/
    @GetMapping("/{storeSN}/menu")
    public String menuView(@PathVariable String storeSN, Model model) {

//        List<Category> categoryList = categoryService.findAll();
        List<Menu> menuList = menuService.findAll();

//        model.addAttribute("storeName", storeName.get());
        model.addAttribute("list" , menuList);
//        model.addAttribute("categoryList", categoryList);
        return "customer/customer_menu";
    }

    /*상세보기부터 작성해보 */
    @GetMapping("/{storeSN}/menu/detail/{id}")
    public String menuDetail(@PathVariable String storeSN, @PathVariable long id, Model model){
        Optional<Menu> menuDetail = menuService.findById(id);

        model.addAttribute("menuDetail",menuDetail.get());
        return "customer/customer_menu_detail";
=======
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
>>>>>>> 4ace27b (Feat : git pull하기(#11))
    }
}

