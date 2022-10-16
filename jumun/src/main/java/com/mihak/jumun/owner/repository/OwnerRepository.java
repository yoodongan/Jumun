package com.mihak.jumun.owner.repository;

import com.mihak.jumun.owner.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByLoginId(String loginId);
}