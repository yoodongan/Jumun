package com.mihak.jumun.cartAndOption;

import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.CartAndOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartAndOptionRepository extends JpaRepository<CartAndOption, Long> {
    List<CartAndOption> findByCart(Cart cart);
}
