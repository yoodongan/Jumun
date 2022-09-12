package com.mihak.jumun.option;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllByStore(Store store);
}
