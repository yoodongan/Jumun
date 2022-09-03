package com.mihak.jumun.cart;

import com.mihak.jumun.cart.dto.CartForm;
import com.mihak.jumun.cartMenuOption.CartMenuOptionService;
import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MenuService menuService;
    private final CartMenuOptionService cartMenuOptionService;

    @PostMapping("{storeSN}/menu/{menuId}")
    public String saveCart(@PathVariable String storeSN,
                           @PathVariable Long menuId, @ModelAttribute CartForm cartForm,
                           HttpServletRequest request, @CookieValue("customerLogin") String customerKey) {

        HttpSession session = request.getSession(true);
        String userNickname = session.getAttribute(customerKey).toString();
        Menu menu = menuService.findById(menuId).get();

        Cart cart = cartService.saveCart(cartForm, userNickname, menu);

        cartMenuOptionService.saveOptions(cart, cartForm.getCheckOptionIds());

        return "redirect:/" + storeSN + "/menu";
    }
}
