package com.mihak.jumun.menu;

import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.entity.StoreAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByName(String name);

    List<Menu> findByStore(Store store);

    Optional<Menu> findByNameAndStore(String name, Store store);

    Optional<Menu> findByNameAndIdNot(String name, Long menuId);

    List<Menu> findByCategoryId(Long categoryId);
}
