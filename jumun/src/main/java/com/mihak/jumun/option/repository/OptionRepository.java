package com.mihak.jumun.option.repository;

import com.mihak.jumun.option.entity.Option;
import com.mihak.jumun.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllByStore(Store store);
}
