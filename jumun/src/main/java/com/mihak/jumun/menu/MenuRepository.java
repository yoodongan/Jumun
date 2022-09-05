package com.mihak.jumun.menu;

import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByName(String name);

}