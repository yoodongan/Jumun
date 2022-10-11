package com.mihak.jumun.cartAndOption;

import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.CartAndOption;
import com.mihak.jumun.entity.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartAndOptionService {

    private final CartAndOptionRepository cartAndOptionRepository;

    public List<CartAndOption> saveOptions(Cart cart, List<Option> options) {

        List<CartAndOption> cartAndOptions = new ArrayList<>();

        if (options == null) {
            return cartAndOptions;
        }

        for (Option option : options) {
            CartAndOption cartAndOption = CartAndOption.builder()
                    .cart(cart)
                    .options(option)
                    .build();
            cartAndOptionRepository.save(cartAndOption);
            cartAndOptions.add(cartAndOption);
        }
        return cartAndOptions;
    }

    public List<CartAndOption> findAllCartAndOptionsByCart(Cart cart) {
        return cartAndOptionRepository.findByCart(cart);
    }

    public void deleteByCart(Cart cart) {
        cartAndOptionRepository.deleteAllByCart(cart);
    }
}
