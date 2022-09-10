package com.mihak.jumun.option;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.exception.OptionNotFoundException;
import com.mihak.jumun.option.form.OptionFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepository;

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
                .store(store)
                .build();
        optionRepository.save(option);
        return option;
    }

    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    public List<Option> findAllByStore(Store store) {
        return optionRepository.findAllByStore(store);
    }
}
