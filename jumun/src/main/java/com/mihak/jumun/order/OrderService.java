package com.mihak.jumun.order;

import com.mihak.jumun.entity.Order;
import com.mihak.jumun.entity.PayStatus;
import com.mihak.jumun.exception.OrderNotFoundException;
import com.mihak.jumun.order.dto.OrderDtoFromCart;
import com.mihak.jumun.order.dto.OrderFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(OrderDtoFromCart orderDtoFromCart, OrderFormDto orderFormDto) {

        Order order = Order.builder()
                .userNickName(orderDtoFromCart.getUserNickName())
                .storeSerialNumber(orderDtoFromCart.getStoreSerialNumber())
                .totalPrice(orderDtoFromCart.getTotalPrice())
                .orderType(orderDtoFromCart.getOrderType())
                .orderedAt(LocalDateTime.now())
                .payType(orderFormDto.getPayType())
                .requests(orderFormDto.getRequests())
                .payStatus(PayStatus.CONTINUE)
                .build();

        order.setCreatedDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order findOrderById(Long id) {
        Optional<Order> findOrder = orderRepository.findById(id);

        if (findOrder.isEmpty()) {
            throw new OrderNotFoundException("해당 주문을 찾을 수 없습니다.");
        }

        return findOrder.get();
    }

}
