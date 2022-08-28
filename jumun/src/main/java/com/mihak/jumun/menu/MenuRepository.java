package com.mihak.jumun.menu;

import com.mihak.jumun.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Optional<Menu> findByName(String name);
}
