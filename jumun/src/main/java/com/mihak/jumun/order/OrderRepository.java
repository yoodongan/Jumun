package com.mihak.jumun.order;

import com.mihak.jumun.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUserNickName(String userNickName);
}
