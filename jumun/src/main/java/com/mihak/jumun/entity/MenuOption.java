package com.mihak.jumun.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENUOPTION_ID")
    private Long id;

    private String name;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;
}
