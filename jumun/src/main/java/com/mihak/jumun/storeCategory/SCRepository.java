package com.mihak.jumun.storeCategory;

import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SCRepository extends JpaRepository<StoreCategory,Long> {
    StoreCategory findByCategory(Category delCate);
}
