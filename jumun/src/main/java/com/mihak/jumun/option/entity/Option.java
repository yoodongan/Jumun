package com.mihak.jumun.option.entity;

import com.mihak.jumun.store.entity.Store;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTIONS_ID")
    private Long id;

    private String name;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public void changeOption(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
