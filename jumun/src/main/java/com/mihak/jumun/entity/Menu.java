package com.mihak.jumun.entity;

import lombok.Builder;
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
    private String imgUrl;  //url

    @Lob
    @Column(nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    /*이미지 업로드를 위한 빌더 추가*/
//    @Builder
//    public Menu(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }


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
    public static Menu createMenu(String name, int price, String description, String imgUrl, Category category, Store store) {
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

        /* S3처리 후 리턴된 이미지url 저장*/
        menu.setImgUrl(imgUrl);

        return menu;
    }
}
