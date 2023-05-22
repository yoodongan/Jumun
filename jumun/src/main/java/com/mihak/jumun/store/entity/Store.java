package com.mihak.jumun.store.entity;

import com.mihak.jumun.owner.entity.Owner;
import com.mihak.jumun.menu.entity.Menu;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private Long id;

    private String name;
    @Embedded
    private Address address;

    @Column(unique = true)
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    // 새롭게 추가한 내용.
    @Builder.Default
    @OneToMany(mappedBy = "store")     // 음식점 url 식별번호로 해당 음식점 메뉴들을 조회할 수 있다.
    private List<Menu> menus = new ArrayList<>();

    public void addMenu(Menu menu) {
        menus.add(menu);
    }
}