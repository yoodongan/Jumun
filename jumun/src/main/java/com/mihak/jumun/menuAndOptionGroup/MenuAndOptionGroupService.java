package com.mihak.jumun.menuAndOptionGroup;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.MenuAndOptionGroup;
import com.mihak.jumun.entity.OptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuAndOptionGroupService {

    private final MenuAndOptionGroupRepository menuAndOptionGroupRepository;

    public List<MenuAndOptionGroup> getMenuAndOptionGroupByMenu(Menu menu) {
        return menuAndOptionGroupRepository.findByMenu(menu);
    }

//    public void addMenuOptionGroups(Menu menu, OptionGroup... optionGroups) {
//        MenuAndOptionGroup menuAndOptionGroup = new MenuAndOptionGroup();
//        for (OptionGroup optionGroup : optionGroups) {
//            menuAndOptionGroup.addMenuAndOptionGroup(menu, optionGroup);
//            menuAndOptionGroupRepository.save(menuAndOptionGroup);
//        }
//    }

    public void addMenuOptionGroup(Menu menu, OptionGroup optionGroup) {
        MenuAndOptionGroup menuAndOptionGroup = new MenuAndOptionGroup();
        menuAndOptionGroup.addMenuAndOptionGroup(menu, optionGroup);
        menuAndOptionGroupRepository.save(menuAndOptionGroup);
    }


    public List<MenuAndOptionGroup> findAllByMenu(Menu menu) {
        return menuAndOptionGroupRepository.findByMenu(menu);
    }

    public List<OptionGroup> getOptionGroupsByMenu(Menu menu) {
        List<MenuAndOptionGroup> allByMenu = findAllByMenu(menu);
        List<OptionGroup> optionGroups = new ArrayList<>();
        for (MenuAndOptionGroup menuAndOptionGroup : allByMenu) {
            optionGroups.add(menuAndOptionGroup.getOptionGroup());
        }
        return optionGroups;
    }

}
