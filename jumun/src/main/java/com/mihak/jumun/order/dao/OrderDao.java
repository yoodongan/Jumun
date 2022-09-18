package com.mihak.jumun.order.dao;

import com.mihak.jumun.order.dto.OrderDtoFromCart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class OrderDao {

    private final RedisTemplate<String, Object> redisTemplate;

    public void addOrderDtoFromCart(OrderDtoFromCart dto, String userNickName) {
        String key = keyGenerate(userNickName);
        redisTemplate.opsForValue().set(key, dto);
        redisTemplate.expire(key, 5, TimeUnit.MINUTES);
    }

    public OrderDtoFromCart getOrderDtoFromCart(String userNickName) {
        String key = keyGenerate(userNickName);
        return (OrderDtoFromCart) redisTemplate.opsForValue().get(key);
    }

    private String keyGenerate(String key) {
        return key + " : orderDto";
    }
}
