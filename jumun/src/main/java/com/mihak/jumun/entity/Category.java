package com.mihak.jumun.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Integer id;

    private String name;

}
