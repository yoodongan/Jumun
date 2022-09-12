package com.mihak.jumun.menuAndOptionGroup;

import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.MenuAndOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuAndOptionGroupRepository extends JpaRepository<MenuAndOptionGroup, Long> {
    List<MenuAndOptionGroup> findByMenu(Menu menu);
}
