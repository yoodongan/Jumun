package com.mihak.jumun.cartMenuOption;

import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.CartMenuOption;
import com.mihak.jumun.entity.MenuOption;
import com.mihak.jumun.menuOption.MenuOptionRepository;
import com.mihak.jumun.menuOption.MenuOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartMenuOptionService {

    private final CartMenuOptionRepository cartMenuOptionRepository;
    private final MenuOptionService menuOptionService;

    public void saveOptions(Cart cart, List<Long> optionIds) {

        for (Long optionId : optionIds) {
            MenuOption menuOption = menuOptionService.findById(optionId);

            CartMenuOption cartMenuOption = CartMenuOption.builder()
                    .cart(cart)
                    .menuOption(menuOption)
                    .build();
            cartMenuOptionRepository.save(cartMenuOption);
        }
    }
}
