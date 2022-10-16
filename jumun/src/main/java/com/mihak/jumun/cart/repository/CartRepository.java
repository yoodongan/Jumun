package com.mihak.jumun.cart.repository;

import com.mihak.jumun.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserNicknameAndIsOrdered(String userNickname, boolean isOrdered);

    Optional<Cart> findById(Long id);
}
