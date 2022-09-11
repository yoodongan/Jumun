package com.mihak.jumun.optionGroup;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.OptionAndOptionGroup;
import com.mihak.jumun.entity.OptionGroup;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.exception.OptionGroupNotFoundException;
import com.mihak.jumun.optionAndOptionGroup.OptionAndOptionGroupService;
import com.mihak.jumun.optionGroup.form.OptionGroupFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionGroupService {
    private final OptionGroupRepository optionGroupRepository;
    private final OptionAndOptionGroupService optionAndOptionGroupService;

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

    public OptionGroup findByIdAndStore(Long optionGroupId, Store store) throws Exception {
        Optional<OptionGroup> optionGroup = optionGroupRepository.findByIdAndStore(optionGroupId, store);
        if(optionGroup.isPresent()) return optionGroup.get();
        else throw new OptionGroupNotFoundException("옵션그룹이 없습니다");

    }

    // 옵션 그룹에 속하는 모든 옵션들 리스팅으로 가져오기.
    public List<Option> findAllByOptionGroup(OptionGroup optionGroup) {
        return optionAndOptionGroupService.findAllByOptionGroup(optionGroup);
    }

}
