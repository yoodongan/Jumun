package com.mihak.jumun.customer;

import com.mihak.jumun.cart.CartService;
import com.mihak.jumun.cart.dto.CartFormDto;
import com.mihak.jumun.cartAndOption.CartAndOptionService;
import com.mihak.jumun.customer.form.CustomerMenuForm;
import com.mihak.jumun.entity.*;
import com.mihak.jumun.menu.MenuService;
import com.mihak.jumun.optionAndOptionGroup.OptionAndOptionGroupService;
import com.mihak.jumun.store.StoreService;
import com.mihak.jumun.storeCategory.SCService;
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


    @GetMapping("/{storeSN}/menu")
    public String menuView(@PathVariable("storeSN") String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findAllByStore(storeSN);
        model.addAttribute("menuList" , menuList);
        model.addAttribute("storeSN", storeSN);

        return "customer/customer_menu";
    }

    @GetMapping("/{storeSN}/menu/{id}/option")
    public String menuDetail(@PathVariable("storeSN") String storeSN, @PathVariable Long id, Model model){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("storeSN", storeSN);
        model.addAttribute("id", id);

        CustomerMenuForm customerMenuForm = customerMenuService.getMenuFormById(id);
        model.addAttribute("customerMenuForm", customerMenuForm);


        return "customer/customer_menu_detail";
    }
    @PostMapping("/{storeSN}/menu/{id}/option")
    public String addToCart(@PathVariable("storeSN") String storeSN, @PathVariable Long id, @ModelAttribute CustomerMenuForm customerMenuForm,
                            HttpServletRequest request, @CookieValue("customerLogin") String customerKey){
//        HttpSession session = request.getSession(true);
//        String userNickname = session.getAttribute(customerKey).toString();
        Menu menu = menuService.findById(id);

        /*options를 form에 새로 추가하기*/


        Cart cart = cartService.addToCart(customerMenuForm, "hi", menu);
        List<CartAndOption> cartAndOptions = cartAndOptionService.saveOptions(cart, customerMenuForm.getCheckOptions());
        cart.updateCartAndOptions(cartAndOptions);

        return "redirect:/" + storeSN + "/menu";
    }
}

