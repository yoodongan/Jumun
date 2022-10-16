package com.mihak.jumun.menuAndOptionGroup.service;
import com.mihak.jumun.menu.entity.Menu;
import com.mihak.jumun.menuAndOptionGroup.entity.MenuAndOptionGroup;
import com.mihak.jumun.optionGroup.entity.OptionGroup;
import com.mihak.jumun.menuAndOptionGroup.repository.MenuAndOptionGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuAndOptionGroupService {

    private final MenuAndOptionGroupRepository menuAndOptionGroupRepository;

    public List<MenuAndOptionGroup> findAllByMenu(Menu menu) {
        return menuAndOptionGroupRepository.findByMenu(menu);
    }

    public void save(Menu menu, OptionGroup optionGroup) {
        MenuAndOptionGroup menuAndOptionGroup = new MenuAndOptionGroup();
        menuAndOptionGroup.addMenuAndOptionGroup(menu, optionGroup);
        menuAndOptionGroupRepository.save(menuAndOptionGroup);
    }

    public List<OptionGroup> getOptionGroupsByMenu(Menu menu) {
        List<MenuAndOptionGroup> allByMenu = findAllByMenu(menu);
        List<OptionGroup> optionGroups = new ArrayList<>();
        for (MenuAndOptionGroup menuAndOptionGroup : allByMenu) {
            optionGroups.add(menuAndOptionGroup.getOptionGroup());
        }
        return optionGroups;
    }

    public void deleteByMenuAndOptionGroup(Menu menu, OptionGroup optionGroup) {
        menuAndOptionGroupRepository.deleteByMenuAndOptionGroup(menu, optionGroup);
    }

    public void deleteByOptionGroup(OptionGroup optionGroup) {
        menuAndOptionGroupRepository.deleteAllByOptionGroup(optionGroup);
    }

    public void deleteByMenu(Menu menu) {
        menuAndOptionGroupRepository.deleteAllByMenu(menu);
    }
}
