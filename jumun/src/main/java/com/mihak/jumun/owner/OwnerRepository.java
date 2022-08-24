package com.mihak.jumun.owner;

import com.mihak.jumun.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByloginId(String loginId);
}