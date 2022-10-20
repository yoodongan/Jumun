package com.mihak.jumun.category.repository;

import com.mihak.jumun.category.entity.Category;
import com.mihak.jumun.owner.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);

    Optional<Category> findByNameAndOwner(String name, Owner owner);
}
