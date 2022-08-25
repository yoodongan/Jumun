package com.mihak.jumun.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    private String name;
    private int price;
    private String imgUrl;

    /*이미지 업로드를 위한 빌더 추가*/
    @Builder
    public Menu(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
