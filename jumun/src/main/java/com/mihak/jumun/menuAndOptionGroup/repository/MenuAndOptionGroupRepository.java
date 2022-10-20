package com.mihak.jumun.menuAndOptionGroup.repository;

import com.mihak.jumun.menu.entity.Menu;
import com.mihak.jumun.menuAndOptionGroup.entity.MenuAndOptionGroup;
import com.mihak.jumun.optionGroup.entity.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface MenuAndOptionGroupRepository extends JpaRepository<MenuAndOptionGroup, Long> {
    List<MenuAndOptionGroup> findByMenu(Menu menu);

    @Transactional
    void deleteByMenuAndOptionGroup(Menu menu, OptionGroup optionGroup);

    @Transactional
    void deleteAllByOptionGroup(OptionGroup optionGroup);

    @Transactional
    void deleteAllByMenu(Menu menu);
}