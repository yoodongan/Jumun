package com.mihak.jumun.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @NotEmpty
    private String name;
    @NotNull
    private int price;

    @Column(nullable = true)
    private String image;  //url

    @Lob
    @Column(nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public void addCategory(Category category) {
        this.setCategory(category);
        // 추후 수정. 카테고리에서 메뉴 조회할 일이 필요하다면 양방향 매핑 설정해야 함.
        // (카테고리 삭제 시 안에 있던 메뉴들 이동이 필요할 때?)
    }

    public void addStore(Store store) {   // restaurant 엔티티와 menu 엔티티 연관관계 맺어줌.
        this.setStore(store);
        store.addMenu(this);
    }

    // 메뉴 생성 메서드
    public static Menu createMenu(String name, int price, String description, String url, Category category, Store store) {
        Menu menu = new Menu();
        menu.setCreatedDate(LocalDateTime.now());
        if(!(description.isEmpty())) {
            menu.setDescription(description);
        }
        menu.setName(name);
        menu.setPrice(price);
        menu.addCategory(category);
        menu.addStore(store);

        /* 옵션 추가 해야 함 */

        /* 이미지 추가해야 함. 일단 첨부파일로 구현하고, 추후 s3에서 이미지 url 로 넣어줄 예정 */
        menu.setImage(url);

        return menu;
    }
}
