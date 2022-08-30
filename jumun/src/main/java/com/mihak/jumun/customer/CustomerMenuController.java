package com.mihak.jumun.customer;

import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.menu.MenuService;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CustomerMenuController {
    private final MenuService menuService;
    private final StoreService storeService;
    private final CategoryService categoryService;

    /*이 부분은 동안님 코드 참고해야겠다.*/
    @GetMapping("/{storeSN}/menu")
    public String menuView(@PathVariable String storeSN, Model model) {

        List<Category> categoryList = categoryService.findAll();
        List<Menu> menuList = menuService.findAll();

//        model.addAttribute("storeName", storeName.get());
        model.addAttribute("list" , menuList);
        model.addAttribute("categoryList", categoryList);
        return "customer/customer_menu";
    }

    /*상세보기부터 작성해보 */
    @GetMapping("/{storeSN}/menu/detail/{id}")
    public String menuDetail(@PathVariable String storeSN, @PathVariable long id, Model model){
        Optional<Menu> menuDetail = menuService.findById(id);

        model.addAttribute("menuDetail",menuDetail.get());
        return "customer/customer_menu_detail";
    }
}

