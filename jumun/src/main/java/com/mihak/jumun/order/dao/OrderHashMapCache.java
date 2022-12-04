package com.mihak.jumun.order.dao;

import com.mihak.jumun.order.dto.OrderDtoFromCart;
import com.mihak.jumun.order.exception.OrderProcessFailedException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderHashMapCache {

    private final Map<String, OrderDtoFromCart> cache = new ConcurrentHashMap<>();

    public void putOrderDtoFromCart(OrderDtoFromCart orderDtoFromCart, String userNickname) {
        cache.put(userNickname, orderDtoFromCart);
    }

    public OrderDtoFromCart getOrderDtoFromCart(String userNickname) {
        if (userNickname != null && cache != null && cache.containsKey(userNickname)) {
            return cache.get(userNickname);
        } else {
            throw new OrderProcessFailedException("주문 절차에서 예외가 발생했습니다.");
        }
    }

    public void removeOrderDtoFromCart(String userNickname) {
        if (userNickname != null && cache != null && cache.containsKey(userNickname)) {
            cache.remove(userNickname);
        } else {
            throw new OrderProcessFailedException("주문 절차에서 예외가 발생했습니다.");
        }
    }

    public int getCount() {
        return cache.size();
    }

    public void clear() {
        cache.clear();
    }
}
