package com.mihak.jumun.optionAndOptionGroup;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.OptionAndOptionGroup;
import com.mihak.jumun.entity.OptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionAndOptionGroupService {

    private final OptionAndOptionGroupRepository optionAndOptionGroupRepository;

    // 옵션 그룹에 옵션 추가
    public void addOption(OptionGroup optionGroup, Option option) {
        OptionAndOptionGroup optionAndOptionGroup = OptionAndOptionGroup.builder()
                .optionGroup(optionGroup)
                .option(option)
                .build();
        optionAndOptionGroupRepository.save(optionAndOptionGroup);
    }
}
