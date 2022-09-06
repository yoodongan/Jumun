package com.mihak.jumun.cartOption;

import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.CartOption;
import com.mihak.jumun.entity.Option;
import com.mihak.jumun.option.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartOptionService {

    private final CartOptionRepository cartOptionRepository;
    private final OptionService optionService;

    public void saveOptions(Cart cart, List<Long> optionIds) {

        for (Long optionId : optionIds) {
            Option option = optionService.findById(optionId);

            CartOption cartOption = CartOption.builder()
                    .cart(cart)
                    .option(option)
                    .build();
            cartOptionRepository.save(cartOption);
        }
    }
}
