package com.mihak.jumun.storeCategory.entity;

import com.mihak.jumun.category.entity.Category;
import com.mihak.jumun.store.entity.Store;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StoreAndCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_CATEGORY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;
}
