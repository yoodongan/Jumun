package com.mihak.jumun.order.dao;

import com.mihak.jumun.order.dto.OrderDtoFromCart;
import com.mihak.jumun.order.exception.OrderProcessFailedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class OrderHashMapCacheTest {

    @Autowired
    private OrderHashMapCache orderHashMapCache;

    @AfterEach
    public void afterEach() {
        orderHashMapCache.clear();
    }

    @Test
    public void multi_thread_test() throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                try {
                    orderHashMapCache.putOrderDtoFromCart(new OrderDtoFromCart(),
                            UUID.randomUUID().toString().substring(0, 10));
                } finally{
                    latch.countDown();
                }
            });
        }
        latch.await();
        assertThat(orderHashMapCache.getCount()).isEqualTo(100);
    }

    @Test
    public void orderProcess_test() {

        String uuid = UUID.randomUUID().toString().substring(0, 10);
        orderHashMapCache.putOrderDtoFromCart(new OrderDtoFromCart(), uuid);

        assertThatThrownBy(() -> orderHashMapCache.removeOrderDtoFromCart(UUID.randomUUID().toString().substring(0, 10)))
                .isInstanceOf(OrderProcessFailedException.class);

        assertThatThrownBy(() -> orderHashMapCache.getOrderDtoFromCart(UUID.randomUUID().toString().substring(0, 10)))
                .isInstanceOf(OrderProcessFailedException.class);

        assertThat(orderHashMapCache.getOrderDtoFromCart(uuid)).isNotNull();
    }

}