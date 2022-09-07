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
    private final OptionService optionService;

    public void saveOptions(Cart cart, List<Option> options) {

        for (Option option : options) {
            CartAndOption cartAndOption = CartAndOption.builder()
                    .cart(cart)
                    .options(option)
                    .build();
            cartAndOptionRepository.save(cartAndOption);
        }
    }

    public List<Option> getOptionsByCart(Cart cart) {
        List<CartAndOption> cartAndOptions = cartAndOptionRepository.findByCart(cart);
        List<Option> options = new ArrayList<>();

        for (CartAndOption cartAndOption : cartAndOptions) {
            Option option = cartAndOption.getOptions();
            options.add(option);
        }
        return options;
    }
}
