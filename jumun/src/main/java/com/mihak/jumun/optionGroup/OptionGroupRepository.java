package com.mihak.jumun.optionGroup;
import com.mihak.jumun.entity.OptionGroup;
import com.mihak.jumun.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {

    List<OptionGroup> findAllByStore(Store store);

    Optional<OptionGroup> findByIdAndStore(Long optionGroupId, Store store);

}
