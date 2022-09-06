package com.mihak.jumun.entity;

import javax.persistence.*;

@Entity
public class OptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTIONGROUP_ID")
    private Long id;

    private String name;
    private boolean isMultiple;
}
