package com.mihak.jumun.option;

import com.mihak.jumun.cartAndOption.CartAndOptionService;
import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.CartAndOption;
import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.exception.OptionNotFoundException;
import com.mihak.jumun.option.form.OptionFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepository;
    private final CartAndOptionService cartAndOptionService;

    public Option findById(Long id) {
        Optional<Option> option = optionRepository.findById(id);

        if (option.isEmpty()) {
            throw new OptionNotFoundException("해당 메뉴 옵션이 존재하지 않습니다.");
        }
        return option.get();
    }

    public Option createOption(OptionFormDto optionFormDto, Store store) {
        Option option = Option.builder()
                .name(optionFormDto.getName())
                .price(optionFormDto.getPrice())
                .build();
        optionRepository.save(option);
        return option;
    }

    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    public List<Option> getOptionsByCart(Cart cart) {
        List<CartAndOption> cartAndOptions = cartAndOptionService.getOptionsByCart(cart);
        List<Option> options = new ArrayList<>();
        for (CartAndOption cartAndOption : cartAndOptions) {
            options.add(cartAndOption.getOptions());
        }
        return options;
    }
}
