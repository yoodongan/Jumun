package com.mihak.jumun.store;

import com.mihak.jumun.entity.Owner;
import com.mihak.jumun.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByserialNumber(String storeSN);

    Optional<Store> findByowner(Owner owner);
}
