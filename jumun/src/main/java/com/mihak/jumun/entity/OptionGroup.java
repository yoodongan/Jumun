package com.mihak.jumun.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class OptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTIONGROUP_ID")
    private Long id;

    private String name;
    private boolean isMultiple;

    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL)
    private List<OptionAndOptionGroup> optionAndOptionGroups = new ArrayList<>();
}
