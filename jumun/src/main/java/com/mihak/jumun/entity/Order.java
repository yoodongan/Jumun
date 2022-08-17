package com.mihak.jumun.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    private String number;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime orderedAt;
    private int totalPrice;
    @Lob
    private String request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;
}
