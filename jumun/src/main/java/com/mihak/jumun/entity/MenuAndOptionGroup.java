package com.mihak.jumun.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MenuAndOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_OPTIONGROUP_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTIONGROUP_ID")
    private OptionGroup optionGroup;

    public void addMenuAndOptionGroup(Menu menu, OptionGroup optionGroup) {
        this.menu = menu;
        this.optionGroup = optionGroup;
    }
}
