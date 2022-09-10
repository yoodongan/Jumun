package com.mihak.jumun.optionGroup;

import com.mihak.jumun.entity.OptionGroup;
import com.mihak.jumun.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {

    List<OptionGroup> findAllByStore(Store store);
}
