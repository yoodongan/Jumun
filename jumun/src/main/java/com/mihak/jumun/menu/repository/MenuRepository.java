package com.mihak.jumun.menu.repository;

import com.mihak.jumun.category.entity.Category;
import com.mihak.jumun.menu.entity.Menu;
import com.mihak.jumun.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByName(String name);

    List<Menu> findByStore(Store store);

    Optional<Menu> findByNameAndStore(String name, Store store);

    Optional<Menu> findByNameAndIdNot(String name, Long menuId);

    List<Menu> findByCategoryId(Long categoryId);

    List<Menu> findByCategoryAndStore(Category category, Store store);
}
