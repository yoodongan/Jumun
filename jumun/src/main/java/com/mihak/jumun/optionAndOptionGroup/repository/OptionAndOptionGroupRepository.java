package com.mihak.jumun.optionAndOptionGroup.repository;
import com.mihak.jumun.option.entity.Option;
import com.mihak.jumun.optionAndOptionGroup.entity.OptionAndOptionGroup;
import com.mihak.jumun.optionGroup.entity.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("select oog from OptionAndOptionGroup oog " +
            "join fetch oog.optionGroup og " +
            "join fetch oog.option o")
    List<OptionAndOptionGroup> findAll();

}
