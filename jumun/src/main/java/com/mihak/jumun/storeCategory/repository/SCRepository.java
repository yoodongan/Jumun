package com.mihak.jumun.storeCategory.repository;

import com.mihak.jumun.category.entity.Category;
import com.mihak.jumun.store.entity.Store;
import com.mihak.jumun.storeCategory.entity.StoreAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SCRepository extends JpaRepository<StoreAndCategory,Long> {
    List<StoreAndCategory> findAllByCategory(Category category);

    StoreAndCategory findByCategory(Category delCate);

    Optional<StoreAndCategory> findByStoreAndCategory(Store store, Category category);

    @Query("select sc from StoreAndCategory sc " +
            "join fetch sc.store s " +
            "join fetch sc.category c"
    )
    List<StoreAndCategory> findAll();
}
