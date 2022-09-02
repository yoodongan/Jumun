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

    @GetMapping("/{storeSN}/menu")
    public String menuView(Model model) {
//        Optional<Store> storeName = storeService.findBySerialNumber();
        //고객은 시리얼넘버를 어디서 뽑아야하는거지??
        //고객은 스토어를 어떻게 찾아가야하는거지?

        //쿠키는 사용자입장에서 요청할 때마다 url에 같이 담아서 주기로 했던거 같은데?

        //뷰파일은 공유할 수 있겠지만 컨트롤러는 나눠야겠는데??



        List<Category> categoryList = categoryService.findAll();
        List<Menu> menuList = menuService.findAll();

//        model.addAttribute("storeName", storeName.get());
        model.addAttribute("list" , menuList);
        model.addAttribute("categoryList", categoryList);
        return "customer/customer_menu";
    }
    @GetMapping("/{storeSN}/menu/detail/{id}")
    public String menuDetail(Model model){
        return "customer/customer_menu_detail";
    }
}

