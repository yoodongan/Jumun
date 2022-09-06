package com.mihak.jumun.entity;

import javax.persistence.*;

@Entity
public class OptionAndOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTIONS_OPTIONGROUP_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTIONS_ID")
    private Option options;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTIONGROUP_ID")
    private OptionGroup optionGroup;
}
