package com.mihak.jumun.order;

import com.mihak.jumun.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final OrderRepository orderRepository;
    public List<Order> findAllByStoreSN(String storeSN) {
        List<Order> list = orderRepository.findAll();
        List<Order> listInStore = new ArrayList<>();
        for(Order order : list) {
            if (order.getStoreSerialNumber().equals(storeSN)) {
                listInStore.add(order);
            }
        }
        return listInStore;
        }

    public Order findbyOrderId(Long orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElseThrow(() -> new RuntimeException("등록된 주문이 없음"));
    }}

