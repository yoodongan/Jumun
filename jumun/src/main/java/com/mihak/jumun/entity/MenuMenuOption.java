package com.mihak.jumun.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    @Column
    private int menuOptionCount;     // 메뉴마다 옵션 수량이 표시될수도, 안될수도 있다.
}
