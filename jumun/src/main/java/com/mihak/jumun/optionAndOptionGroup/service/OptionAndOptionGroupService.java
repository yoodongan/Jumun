package com.mihak.jumun.optionAndOptionGroup.service;

import com.mihak.jumun.option.entity.Option;
import com.mihak.jumun.option.service.OptionService;
import com.mihak.jumun.optionAndOptionGroup.entity.OptionAndOptionGroup;
import com.mihak.jumun.optionAndOptionGroup.repository.OptionAndOptionGroupRepository;
import com.mihak.jumun.optionGroup.entity.OptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OptionAndOptionGroupService {
    private final OptionAndOptionGroupRepository optionAndOptionGroupRepository;
    private final OptionService optionService;

    // 옵션 그룹에 옵션 추가, 중간테이블의 정보가 담긴 리스트를 리턴.
    public void addOption(OptionGroup optionGroup, Option option) {
        Optional<OptionAndOptionGroup> oog = optionAndOptionGroupRepository.findByOptionGroupAndOption(optionGroup, option);
        if(oog.isPresent()) return;
        OptionAndOptionGroup optionAndOptionGroup = OptionAndOptionGroup.builder()
                .option(option)
                .optionGroup(optionGroup)
                .build();
        optionAndOptionGroupRepository.save(optionAndOptionGroup);
    }

    public List<Option> getOptionsByOptionGroup(OptionGroup optionGroup) {
        List<OptionAndOptionGroup> allByOptionGroup = optionAndOptionGroupRepository.findAllByOptionGroup(optionGroup);
        List<Option> options = allByOptionGroup.stream()
                .map(OptionAndOptionGroup::getOption)
                .collect(Collectors.toList());
        return options;
    }

    public void deleteByOptionId(Long optionId) {
        Option option = optionService.findById(optionId);
        optionAndOptionGroupRepository.deleteAllByOption(option);
    }

    @Transactional
    public void deleteByOptionGroup(OptionGroup optionGroup) {
        optionAndOptionGroupRepository.deleteAllByOptionGroup(optionGroup);
    }

    public void deleteByOption(Option option) {
        optionAndOptionGroupRepository.deleteAllByOption(option);
    }

    public List<OptionAndOptionGroup> findAll() {
        List<OptionAndOptionGroup> all = optionAndOptionGroupRepository.findAll();
        all.stream().forEach(oog -> {
            oog.getOptionGroup().getName();
            oog.getOptionGroup().getName();
        });
        return all;
    }
}
