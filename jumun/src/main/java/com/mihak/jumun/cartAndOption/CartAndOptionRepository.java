package com.mihak.jumun.cartAndOption;

import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.CartAndOption;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartAndOptionRepository extends JpaRepository<CartAndOption, Long> {
    List<CartAndOption> findByCart(Cart cart);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM CartAndOption cao WHERE cao.cart = :cart")
    void deleteAllByCart(@Param("cart") Cart cart);
}
