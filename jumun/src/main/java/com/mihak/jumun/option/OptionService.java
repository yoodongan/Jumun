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

    public Option save(OptionFormDto optionFormDto, Store store) {
        Option option = Option.builder()
                .name(optionFormDto.getName())
                .price(optionFormDto.getPrice())
                .store(store)
                .build();
        optionRepository.save(option);
        return option;
    }

    public List<Option> findAllByStore(Store store) {
        return optionRepository.findAllByStore(store);
    }

    public List<Option> getOptionsByCart(Cart cart) {
        List<CartAndOption> cartAndOptions = cartAndOptionService.findAllCartAndOptionsByCart(cart);
        List<Option> options = new ArrayList<>();
        for (CartAndOption cartAndOption : cartAndOptions) {
            options.add(cartAndOption.getOptions());
        }
        return options;
    }

    /* 수정 */
    // 수정폼 보여주기
    public OptionFormDto getOptionFormDtoByOption(Option option) {
        OptionFormDto optionFormDto = OptionFormDto.builder()
                .name(option.getName())
                .price(option.getPrice())
                .store(option.getStore())
                .build();
        return optionFormDto;
    }

    public void modify(Long optionId, OptionFormDto optionFormDto) {
        Option option = optionRepository.findById(optionId).get();
        option.changeOption(optionFormDto.getName(), optionFormDto.getPrice());
        optionRepository.save(option);
    }

    public void deleteById(Long optionId) {
        optionRepository.deleteById(optionId);
    }
}
