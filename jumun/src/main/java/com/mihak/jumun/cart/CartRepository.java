package com.mihak.jumun.cart;

import com.mihak.jumun.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByNicknameAndIsOrdered(String Nickname, boolean isOrdered);

    Optional<Cart> findById(Long id);
}
