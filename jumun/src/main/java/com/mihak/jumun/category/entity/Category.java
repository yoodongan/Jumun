package com.mihak.jumun.category.entity;

import com.mihak.jumun.global.domain.BaseEntity;
import com.mihak.jumun.owner.entity.Owner;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private Owner owner;
}