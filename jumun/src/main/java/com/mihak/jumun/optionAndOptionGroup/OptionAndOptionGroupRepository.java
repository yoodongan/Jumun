package com.mihak.jumun.optionAndOptionGroup;

import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.MenuAndOptionGroup;
import com.mihak.jumun.entity.OptionAndOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionAndOptionGroupRepository extends JpaRepository<OptionAndOptionGroup, Long> {
/*옵션그룹아이디로 옵션을 찾자*/
List<OptionAndOptionGroup> findOptionsByOptionGroupId(Menu menu);
}
