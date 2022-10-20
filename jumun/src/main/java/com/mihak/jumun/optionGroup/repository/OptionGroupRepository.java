package com.mihak.jumun.optionGroup.repository;
import com.mihak.jumun.optionGroup.entity.OptionGroup;
import com.mihak.jumun.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {

    List<OptionGroup> findAllByStore(Store store);

    Optional<OptionGroup> findByIdAndStore(Long optionGroupId, Store store);

}
