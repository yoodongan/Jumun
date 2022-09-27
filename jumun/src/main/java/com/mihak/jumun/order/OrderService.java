package com.mihak.jumun.order;

import com.mihak.jumun.cart.CartService;
import com.mihak.jumun.cart.dto.CartDto;

import com.mihak.jumun.entity.Order;
import com.mihak.jumun.entity.OrderStatus;
import com.mihak.jumun.entity.PayStatus;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.exception.OrderNotFoundException;
import com.mihak.jumun.order.dto.OrderDtoFromCart;
import com.mihak.jumun.order.dto.OrderFormDto;

import com.mihak.jumun.storeMgmt.dto.FindByUserDailyDto;
import com.mihak.jumun.storeMgmt.dto.FindListFormDto;
import com.mihak.jumun.pay.dto.PaySuccessDto;

import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    public PaySuccessDto getPaySuccessDto(Order order) {

        List<CartDto> orderHistory = cartService.getCartByUserNickName(order.getUserNickName(), true);

        return PaySuccessDto.builder()
                .userNickName(order.getUserNickName())
                .orderId(order.getId())
                .orderHistory(orderHistory)
                .orderStatus(order.getOrderStatus())
                .orderTotalPrice(order.getTotalPrice())
                .storeSN(order.getStoreSerialNumber())
                .build();
    }
    
    public List<Order> findAllbyStoreId(String storeSN) {
        List<Order> li = orderRepository.findAll();
        List<Order> findList = new ArrayList<>();
        for (Order list : li) {
            if (list.getStoreSerialNumber().equals(storeSN)) {
                findList.add(list);
            }
        }
            //최신순 정렬
        Comparator<Order> comparator = Comparator.comparing(Order::getOrderedAt);
        Collections.sort(findList, comparator.reversed());

        return findList;
    }

    public List<FindListFormDto> findAllbyPriceDaily(String storeSN) {
        List<FindListFormDto> findList = orderRepository.findByPriceDaily(storeSN);
        return findList;
    }

    public List<FindByUserDailyDto> findAllbyUserDaily(String storeSN) {
        List<FindByUserDailyDto> findList = orderRepository.findByUserDaily(storeSN);
        return findList;
    }

// 같은 날짜(key)를 갖는다면 value를 합산
    public Map<String, Long> sum(List<FindListFormDto> list) {
        return list.stream().collect(Collectors.toMap(e -> e.getChangeOrderedAt(), e -> e.getTotalPrice(), Long::sum));
    }

    public Map<String, Long> sumByUser(List<FindByUserDailyDto> list) {
        return list.stream().collect(Collectors.toMap(e -> e.getChangeOrderedAt(), e -> e.getUserNickName(), Long::sum));
    }
}
