package com.mihak.jumun.entity;

import javax.persistence.*;

@Entity
public class OptionAndOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_OPTIONGROUP_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTION_ID")
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTIONGROUP_ID")
    private OptionGroup optionGroup;
}
