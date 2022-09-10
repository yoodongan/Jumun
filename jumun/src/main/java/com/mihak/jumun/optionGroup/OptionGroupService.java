package com.mihak.jumun.optionGroup;

import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.MenuAndOptionGroup;
import com.mihak.jumun.entity.OptionGroup;
import com.mihak.jumun.menuAndOptionGroup.MenuAndOptionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionGroupService {

    private final OptionGroupRepository optionGroupRepository;
    private final MenuAndOptionGroupService menuAndOptionGroupService;

    public List<OptionGroup> getOptionGroupByMenu(Menu menu) {
        List<MenuAndOptionGroup> menuAndOptionGroupByMenu = menuAndOptionGroupService.getMenuAndOptionGroupByMenu(menu);
        List<OptionGroup> optionGroups = new ArrayList<>();

        for (MenuAndOptionGroup menuAndOptionGroup : menuAndOptionGroupByMenu) {
            OptionGroup optionGroup = menuAndOptionGroup.getOptionGroup();
            optionGroups.add(optionGroup);
        }
        return optionGroups;
    }

}
