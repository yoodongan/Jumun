package com.mihak.jumun.cart;

import com.mihak.jumun.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserNickNameAndIsOrdered(String userNickName, boolean isOrdered);
}
