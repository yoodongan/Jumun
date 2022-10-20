package com.mihak.jumun.store.repository;

import com.mihak.jumun.owner.entity.Owner;
import com.mihak.jumun.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findBySerialNumber(String storeSN);

    Optional<Store> findByOwner(Owner owner);
}
