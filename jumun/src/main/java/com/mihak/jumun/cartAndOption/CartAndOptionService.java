package com.mihak.jumun.cartAndOption;

import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.CartAndOption;
import com.mihak.jumun.entity.Option;
import com.mihak.jumun.option.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartAndOptionService {

    private final CartAndOptionRepository cartAndOptionRepository;

    public void saveOptions(Cart cart, List<Option> options) {

        for (Option option : options) {
            CartAndOption cartAndOption = CartAndOption.builder()
                    .cart(cart)
                    .options(option)
                    .build();
            cartAndOptionRepository.save(cartAndOption);
        }
    }

    public List<CartAndOption> getOptionsByCart(Cart cart) {
        return cartAndOptionRepository.findByCart(cart);
    }
}
