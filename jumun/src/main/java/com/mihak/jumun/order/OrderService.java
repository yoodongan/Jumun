package com.mihak.jumun.order;

import com.mihak.jumun.cart.CartService;
import com.mihak.jumun.cart.dto.CartDto;
import com.mihak.jumun.entity.Order;
import com.mihak.jumun.entity.OrderStatus;
import com.mihak.jumun.entity.PayStatus;
import com.mihak.jumun.exception.OrderNotFoundException;
import com.mihak.jumun.order.dto.OrderDtoFromCart;
import com.mihak.jumun.order.dto.OrderFormDto;
import com.mihak.jumun.pay.dto.KakaoPaySuccessDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;

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
                .orderStatus(OrderStatus.BEFOREORDER)
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

    public Order findOrderByUserNickName(String userNickName) {
        Optional<Order> findOrder = orderRepository.findByUserNickName(userNickName);

        if (findOrder.isEmpty()) {
            throw new OrderNotFoundException("해당 주문을 찾을 수 없습니다.");
        }

        return findOrder.get();
    }

    @Transactional
    public void cancelOrderByPayFail(Long orderId) {
        Order order = findOrderById(orderId);

        order.setOrderStatus(OrderStatus.CANCEL);
        order.setPayStatus(PayStatus.REFUSE);
    }

    @Transactional
    public void cancelOrderByUser(Long orderId) {
        Order order = findOrderById(orderId);

        order.setOrderStatus(OrderStatus.CANCEL);
        order.setPayStatus(PayStatus.REFUND);

        cartService.cancelOrder(order.getUserNickName());
    }

    public KakaoPaySuccessDto getKakaoPaySuccessDto(Order order) {

        List<CartDto> orderHistory = cartService.getCartByUserNickName(order.getUserNickName(), true);

        return KakaoPaySuccessDto.builder()
                .userNickName(order.getUserNickName())
                .orderId(order.getId())
                .orderHistory(orderHistory)
                .orderStatus(order.getOrderStatus())
                .orderTotalPrice(order.getTotalPrice())
                .storeSN(order.getStoreSerialNumber())
                .build();
    }
}
