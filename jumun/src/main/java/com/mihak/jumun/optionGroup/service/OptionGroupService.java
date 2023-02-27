package com.mihak.jumun.optionGroup.service;

import com.mihak.jumun.menu.entity.Menu;
import com.mihak.jumun.menuAndOptionGroup.entity.MenuAndOptionGroup;
import com.mihak.jumun.menuAndOptionGroup.service.MenuAndOptionGroupService;
import com.mihak.jumun.optionAndOptionGroup.service.OptionAndOptionGroupService;
import com.mihak.jumun.optionGroup.dto.OptionGroupFormDto;
import com.mihak.jumun.optionGroup.entity.OptionGroup;
import com.mihak.jumun.optionGroup.repository.OptionGroupRepository;
import com.mihak.jumun.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OptionGroupService {
    private final OptionGroupRepository optionGroupRepository;
    private final MenuAndOptionGroupService menuAndOptionGroupService;
    private final OptionAndOptionGroupService optionAndOptionGroupService;

    public List<OptionGroup> getOptionGroupsByMenu(Menu menu) {
        List<MenuAndOptionGroup> menuAndOptionGroupByMenu = menuAndOptionGroupService.findAllByMenu(menu);
        List<OptionGroup> optionGroups = menuAndOptionGroupByMenu.stream()
                .map(MenuAndOptionGroup::getOptionGroup)
                .collect(Collectors.toList());

        return optionGroups;

    }

    public void save(OptionGroupFormDto optionGroupFormDto) {
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
        else return null;

    }

    // 옵션그룹 수정
    public OptionGroupFormDto getOptionGroupFormDtoByOptionGroup(OptionGroup optionGroup) {
        OptionGroupFormDto optionGroupFormDto = OptionGroupFormDto.builder()
                .name(optionGroup.getName())
                .isMultiple(optionGroup.isMultiple())
                .store(optionGroup.getStore())
                .build();
        return optionGroupFormDto;
    }


    public void modify(Long optionGroupId, OptionGroupFormDto optionGroupFormDto) {
        OptionGroup optionGroup = optionGroupRepository.findById(optionGroupId).get();
        optionGroup.changeOptionGroup(optionGroupFormDto.getName(), optionGroupFormDto.getIsMultiple());
        optionGroupRepository.save(optionGroup);
    }

    // 옵션그룹 삭제 (옵션가지고 있을 시)
    public void deleteByOptionGroup(OptionGroup optionGroup) {
        optionGroupRepository.deleteById(optionGroup.getId());
        optionAndOptionGroupService.deleteByOptionGroup(optionGroup);
    }
}
