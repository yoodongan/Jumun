package com.mihak.jumun.menuAndOptionGroup;

import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.MenuAndOptionGroup;
import com.mihak.jumun.entity.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface MenuAndOptionGroupRepository extends JpaRepository<MenuAndOptionGroup, Long> {
    List<MenuAndOptionGroup> findByMenu(Menu menu);

    @Transactional
    void deleteByMenuAndOptionGroup(Menu menu, OptionGroup optionGroup);

    void deleteAllByOptionGroup(OptionGroup optionGroup);
}