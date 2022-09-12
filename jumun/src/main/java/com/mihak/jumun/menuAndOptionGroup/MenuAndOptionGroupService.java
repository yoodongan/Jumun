package com.mihak.jumun.menuAndOptionGroup;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.MenuAndOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuAndOptionGroupService {

    private final MenuAndOptionGroupRepository menuAndOptionGroupRepository;

    public List<MenuAndOptionGroup> getMenuAndOptionGroupByMenu(Menu menu) {
        return menuAndOptionGroupRepository.findByMenu(menu);
    }
}
