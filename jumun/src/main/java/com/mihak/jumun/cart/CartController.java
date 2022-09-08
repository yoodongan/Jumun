package com.mihak.jumun.cart;

import com.mihak.jumun.cart.dto.CartDetailDto;
import com.mihak.jumun.cart.dto.CartDto;
import com.mihak.jumun.cart.dto.CartFormDto;
import com.mihak.jumun.cartAndOption.CartAndOptionService;
import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MenuService menuService;
    private final CartAndOptionService cartAndOptionService;

    @GetMapping("/{storeSN}/cart")
    public String showCart(@PathVariable String storeSN, Model model,
                           HttpServletRequest request, @CookieValue("customerLogin") String customerKey) {

        HttpSession session = request.getSession(true);
        String userNickname = session.getAttribute(customerKey).toString();

        List<CartDto> cartDtoList = cartService.getCartByUserNickName(userNickname, false);
        model.addAttribute("cartList", cartDtoList);
        model.addAttribute("storeSN", storeSN);
        return "cart/cart_list";
    }

    @PostMapping("/{storeSN}/menu/{menuId}")
    public String saveCart(@PathVariable String storeSN,
                           @PathVariable Long menuId, @ModelAttribute CartFormDto cartFormDto,
                           HttpServletRequest request, @CookieValue("customerLogin") String customerKey) {

        HttpSession session = request.getSession(true);
        String userNickname = session.getAttribute(customerKey).toString();
        Menu menu = menuService.findById(menuId);

        Cart cart = cartService.saveCart(cartFormDto, userNickname, menu);
        cartAndOptionService.saveOptions(cart, cartFormDto.getCheckOptions());

        return "redirect:/" + storeSN + "/menu";
    }

    @GetMapping("/{storeSN}/cart/detail/{cartId}")
    public String getCartDetail(@PathVariable String storeSN, @PathVariable Long cartId,
                            Model model, HttpServletRequest request,
                            @CookieValue("customerLogin") String customerKey) {
        CartDetailDto cartDetailDto = cartService.getCartDetailDtoById(cartId);
        model.addAttribute("cartDetailDto", cartDetailDto);
        return "cart/cart_detail";
    }

    @GetMapping("/{storeSN}/cart/delete/{cartId}")
    public String deleteCart(@PathVariable String storeSN, @PathVariable Long cartId) {
        cartService.deleteCartById(cartId);
        return "redirect:/" + storeSN + "/cart";
    }


    @GetMapping("{storeSN}/cart/modify/{cartId}")
    public String getCartModifyForm(@PathVariable String storeSN, @PathVariable Long cartId,
                             Model model, HttpServletRequest request,
                             @CookieValue("customerLogin") String customerKey) {
        CartFormDto cartFormDto = cartService.getCartFormById(cartId);
        model.addAttribute("cartFormDto", cartFormDto);
        model.addAttribute("storeSN", storeSN);
        model.addAttribute("cardId", cartId);
        return "cart/cart_modify_form";
    }

    @PostMapping("{storeSN}/cart/modify/{cartId}")
    public String modifyCart(@PathVariable String storeSN, @PathVariable Long cartId,
                             @ModelAttribute CartFormDto cartFormDto, HttpServletRequest request,
                             @CookieValue("customerLogin") String customerKey) {

        cartService.modifyCart(cartId, cartFormDto);
        return "redirect:/" + storeSN + "/cart";
    }
}
