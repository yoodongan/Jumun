package com.mihak.jumun.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long id;

    private String userNickName;
    private int count;
    private boolean isOrdered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartAndOption> cartAndOptions = new ArrayList<>();

    public void updateCartAndOptions(List<CartAndOption> cartAndOptions) {
        for (CartAndOption cartAndOption : cartAndOptions) {
            this.cartAndOptions.add(cartAndOption);
        }
    }

    public void modifyCart(int count, List<CartAndOption> cartAndOptions) {
        this.count = count;
        this.cartAndOptions = new ArrayList<>();
        updateCartAndOptions(cartAndOptions);
    }
}
