package com.mihak.jumun.cart;

import com.mihak.jumun.cart.dto.CartForm;
import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart saveCart(CartForm cartForm, String userNickName, Menu menu) {
        Cart cart = Cart.builder().
                userNickName(userNickName)
                .count(cartForm.getCount())
                .isOrdered(false)
                .menu(menu)
                .build();

        return cartRepository.save(cart);
    }
}
