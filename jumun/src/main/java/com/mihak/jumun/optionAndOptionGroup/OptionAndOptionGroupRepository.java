package com.mihak.jumun.optionAndOptionGroup;
import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.OptionAndOptionGroup;
import com.mihak.jumun.entity.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionAndOptionGroupRepository extends JpaRepository<OptionAndOptionGroup, Long> {
    List<OptionAndOptionGroup> findAllByOptionGroup(OptionGroup optionGroup);

    void deleteAllByOption(Option option);
}
