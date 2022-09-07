package com.mihak.jumun.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTIONGROUP_ID")
    private Long id;

    private String name;
    private boolean isMultiple;
}
