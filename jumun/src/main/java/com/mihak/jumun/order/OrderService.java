package com.mihak.jumun.order;

import com.mihak.jumun.entity.Order;
import com.mihak.jumun.entity.PayStatus;
import com.mihak.jumun.exception.OrderNotFoundException;
import com.mihak.jumun.order.dto.OrderDtoFromCart;
import com.mihak.jumun.order.dto.OrderFormDto;
import com.mihak.jumun.storeMgmt.dto.FindByUserDailyDto;
import com.mihak.jumun.storeMgmt.dto.FindListFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Order> findAllbyStoreId(String storeSN) {
        List<Order> li = orderRepository.findAll();
        List<Order> findList = new ArrayList<>();
        for (Order list : li) {
            if (list.getStoreSerialNumber().equals(storeSN)) {
                findList.add(list);
            }
        }
        return findList;
    }

    public List<FindListFormDto> findAllbyPriceDaily() {
        List<FindListFormDto> findList = orderRepository.findByPriceDaily();
        return findList;
    }

    public List<FindByUserDailyDto> findAllbyUserDaily() {
        List<FindByUserDailyDto> findList = orderRepository.findByUserDaily();
        return findList;
    }

// JPQL에서 지원하지 않는 date함수
// 날짜포맷에서 시간초를 제외
// 같은 날짜(key)를 갖는다면 value를 합산
    public Map<String, Long> sum(List<FindListFormDto> list) {
        return list.stream().collect(Collectors.toMap(e -> e.getOrderedAt(), e -> e.getTotalPrice(), Long::sum));
    }

    public Map<String, Long> sumByUser(List<FindByUserDailyDto> list) {
        return list.stream().collect(Collectors.toMap(e -> e.getOrderedAt(), e -> e.getUserNickName(), Long::sum));
    }
}
