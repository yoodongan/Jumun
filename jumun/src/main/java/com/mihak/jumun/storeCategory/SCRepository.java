package com.mihak.jumun.storeCategory;

import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.entity.StoreAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SCRepository extends JpaRepository<StoreAndCategory,Long> {
    List<StoreAndCategory> findAllByCategory(Category category);

    StoreAndCategory findByCategory(Category delCate);

    Optional<StoreAndCategory> findByStoreAndCategory(Store store, Category category);
}
