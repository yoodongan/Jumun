package com.mihak.jumun.customer.controller;

import com.mihak.jumun.cart.entity.Cart;
import com.mihak.jumun.cart.service.CartService;
import com.mihak.jumun.cartAndOption.entity.CartAndOption;
import com.mihak.jumun.cartAndOption.service.CartAndOptionService;
import com.mihak.jumun.category.entity.Category;
import com.mihak.jumun.customer.service.CustomerMenuService;
import com.mihak.jumun.customer.dto.MenuDetailFormDto;
import com.mihak.jumun.menu.entity.Menu;
import com.mihak.jumun.menu.service.MenuService;
import com.mihak.jumun.store.entity.Store;
import com.mihak.jumun.store.service.StoreService;
import com.mihak.jumun.storeCategory.service.SCService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerMenuController {
    private final MenuService menuService;
    private final StoreService storeService;
    private final SCService scService;
    private final CustomerMenuService customerMenuService;
    private final CartService cartService;
    private final CartAndOptionService cartAndOptionService;


    /*기본창*/
    @GetMapping("/{storeSN}/menu")
    public String showMenuView(@PathVariable("storeSN") String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findAllByStore(storeSN);
        model.addAttribute("menuList" , menuList);
        model.addAttribute("storeSN", storeSN);

        return "customer/customer_menu";
    }
    /*다른 카테고리를 눌렀을 때.*/
    @GetMapping("/{storeSN}/menu/{categoryId}")
    public String showMenuView(@PathVariable("storeSN") String storeSN, @PathVariable("categoryId") Long categoryId, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findAllByCategoryId(categoryId);
        model.addAttribute("categoryId" , categoryId);
        model.addAttribute("menuList" , menuList);
        model.addAttribute("storeSN", storeSN);

        return "customer/customer_menu";
    }

    @GetMapping("/{storeSN}/menu/{id}/option")
    public String showMenuDetail(@PathVariable("storeSN") String storeSN, @PathVariable Long id, Model model){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("storeSN", storeSN);
        model.addAttribute("id", id);

        MenuDetailFormDto menuDetailFormDto = customerMenuService.getMenuFormById(id);
        model.addAttribute("menuDetailFormDto", menuDetailFormDto);

        return "customer/customer_menu_detail";
    }
    @PostMapping("/{storeSN}/menu/{id}/option")
    public String saveCart(@PathVariable("storeSN") String storeSN, @PathVariable Long id, @ModelAttribute MenuDetailFormDto menuDetailFormDto,
                            HttpServletRequest request, @CookieValue("customerLogin") String customerKey){
        HttpSession session = request.getSession(true);
        String userNickname = session.getAttribute(customerKey).toString();
        Menu menu = menuService.findById(id);

        Cart cart = cartService.save(menuDetailFormDto, userNickname, menu);
        List<CartAndOption> cartAndOptions = cartAndOptionService.saveOptions(cart, menuDetailFormDto.getCheckOptions());

        return "redirect:/" + storeSN + "/menu";
    }
}

