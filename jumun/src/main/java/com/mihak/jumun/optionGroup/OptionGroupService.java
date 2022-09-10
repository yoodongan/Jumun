package com.mihak.jumun.optionGroup;

import com.mihak.jumun.entity.OptionGroup;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.optionGroup.form.OptionGroupFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionGroupService {
    private final OptionGroupRepository optionGroupRepository;

    public void createOptionGroup(OptionGroupFormDto optionGroupFormDto) {
        OptionGroup optionGroup = OptionGroup.builder()
                .name(optionGroupFormDto.getName())
                .isMultiple(optionGroupFormDto.getIsMultiple())
                .store(optionGroupFormDto.getStore())
                .build();
        optionGroupRepository.save(optionGroup);
    }

    public List<OptionGroup> findAllByStore(Store store) {
        return optionGroupRepository.findAllByStore(store);
    }
}
