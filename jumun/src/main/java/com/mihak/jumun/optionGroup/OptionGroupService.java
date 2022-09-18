package com.mihak.jumun.optionGroup;
import com.mihak.jumun.entity.*;
import com.mihak.jumun.exception.OptionGroupNotFoundException;
import com.mihak.jumun.menuAndOptionGroup.MenuAndOptionGroupService;
import com.mihak.jumun.optionAndOptionGroup.OptionAndOptionGroupService;
import com.mihak.jumun.optionGroup.form.OptionGroupFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionGroupService {
    private final OptionGroupRepository optionGroupRepository;
    private final MenuAndOptionGroupService menuAndOptionGroupService;
    private final OptionAndOptionGroupService optionAndOptionGroupService;

    public List<OptionGroup> getOptionGroupByMenu(Menu menu) {
        List<MenuAndOptionGroup> menuAndOptionGroupByMenu = menuAndOptionGroupService.getMenuAndOptionGroupByMenu(menu);
        List<OptionGroup> optionGroups = new ArrayList<>();

        for (MenuAndOptionGroup menuAndOptionGroup : menuAndOptionGroupByMenu) {
            OptionGroup optionGroup = menuAndOptionGroup.getOptionGroup();
            optionGroups.add(optionGroup);
        }
        return optionGroups;

    }

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

    public OptionGroup findByIdAndStore(Long optionGroupId, Store store) {
        Optional<OptionGroup> optionGroup = optionGroupRepository.findByIdAndStore(optionGroupId, store);
        if(optionGroup.isPresent()) return optionGroup.get();
        else throw new OptionGroupNotFoundException("옵션그룹이 없습니다");

    }

    // 옵션 그룹에 속하는 모든 옵션들 리스팅으로 가져오기.
    public List<Option> findAllByOptionGroup(OptionGroup optionGroup) {
        return optionAndOptionGroupService.findAllByOptionGroup(optionGroup);
    }

    // 옵션그룹 수정
    public OptionGroupFormDto getOptionGroupFormDtd(OptionGroup optionGroup) {
        OptionGroupFormDto optionGroupFormDto = OptionGroupFormDto.builder()
                .name(optionGroup.getName())
                .isMultiple(optionGroup.isMultiple())
                .store(optionGroup.getStore())
                .build();
        return optionGroupFormDto;
    }


    public void modifyOptionGroup(Long optionGroupId, OptionGroupFormDto optionGroupFormDto) {
        OptionGroup optionGroup = optionGroupRepository.findById(optionGroupId).get();
        optionGroup.changeOptionGroup(optionGroupFormDto.getName(), optionGroupFormDto.getIsMultiple());
        optionGroupRepository.save(optionGroup);
    }

    // 옵션그룹 삭제
    public void remove(Long optionGroupId) {
        optionGroupRepository.deleteById(optionGroupId);
    }
    // 옵션그룹 삭제 (옵션가지고 있을 시)
    @Transactional
    public void removeOptionGroup(OptionGroup optionGroup) {
        remove(optionGroup.getId());
        optionAndOptionGroupService.deleteAllByOptionGroup(optionGroup);

    }


}
