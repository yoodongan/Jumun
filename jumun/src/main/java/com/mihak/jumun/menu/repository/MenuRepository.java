package com.mihak.jumun.menu.repository;

import com.mihak.jumun.category.entity.Category;
import com.mihak.jumun.menu.entity.Menu;
import com.mihak.jumun.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByName(String name);

    List<Menu> findByStore(Store store);

    Optional<Menu> findByNameAndStore(String name, Store store);

    Optional<Menu> findByNameAndIdNot(String name, Long menuId);

    @Query("select m from Menu m " +
            "join fetch m.category c " +
            "where m.category.id = :id"
    )
    List<Menu> findByCategoryId(@Param("id") Long categoryId);

    @Query("select m from Menu m " +
            "join fetch m.category c " +
            "join fetch m.store s " +
            "where m.store = :store and m.category = :category"
    )
    List<Menu> findByCategoryAndStore(@Param("category") Category category, @Param("store") Store store);
}
