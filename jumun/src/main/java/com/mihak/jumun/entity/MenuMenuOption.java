package com.mihak.jumun.entity;

import javax.persistence.*;

@Entity
public class MenuMenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_MENUOPTION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENUOPTION_ID")
    private MenuOption menuOption;
}
