package com.mihak.jumun.menu;

import com.mihak.jumun.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long restaurantId);
}
