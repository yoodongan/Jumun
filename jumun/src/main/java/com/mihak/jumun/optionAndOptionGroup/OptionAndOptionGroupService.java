package com.mihak.jumun.optionAndOptionGroup;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.OptionAndOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionAndOptionGroupService {
    private final OptionAndOptionGroupRepository optionAndOptionGroupRepository;
    public List<OptionAndOptionGroup> findOptionsByOptionGroupId() {
        return optionAndOptionGroupRepository.findAll();
    }
}
