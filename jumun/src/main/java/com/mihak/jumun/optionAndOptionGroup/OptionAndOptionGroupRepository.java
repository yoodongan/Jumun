package com.mihak.jumun.optionAndOptionGroup;
import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.OptionAndOptionGroup;
import com.mihak.jumun.entity.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OptionAndOptionGroupRepository extends JpaRepository<OptionAndOptionGroup, Long> {
    List<OptionAndOptionGroup> findAllByOptionGroup(OptionGroup optionGroup);

    @Transactional
    void deleteAllByOption(Option option);

    @Transactional
    void deleteAllByOptionGroup(OptionGroup optionGroup);

    Optional<OptionAndOptionGroup> findByOptionGroupAndOption(OptionGroup optionGroup, Option option);
}
