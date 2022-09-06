package com.mihak.jumun.cartMenuOption;

import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.CartMenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartMenuOptionRepository extends JpaRepository<CartMenuOption, Long> {
    List<CartMenuOption> findByCart(Cart cart);
}
