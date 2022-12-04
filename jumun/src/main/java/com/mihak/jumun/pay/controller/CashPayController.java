package com.mihak.jumun.pay.controller;

import com.mihak.jumun.cart.service.CartService;
import com.mihak.jumun.order.dao.OrderHashMapCache;
import com.mihak.jumun.order.entity.Order;
import com.mihak.jumun.order.service.OrderService;
import com.mihak.jumun.pay.dto.PaySuccessDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CashPayController {

    private final OrderService orderService;
    private final CartService cartService;
    private final OrderHashMapCache orderHashMapCache;

    @GetMapping("/cashPaySuccess/{orderId}")
    public String showCashPayHistory(@PathVariable Long orderId, Model model) {

        Order order = orderService.findById(orderId);
        cartService.modifyIsOrdered(order);

        PaySuccessDto paySuccessDto = orderService.getPaySuccessDto(order);

        orderHashMapCache.removeOrderDtoFromCart(paySuccessDto.getUserNickName());
        model.addAttribute("paySuccessDto", paySuccessDto);
        return "pay/cashPaySuccess";
    }

    @GetMapping("/{storeSN}/cashpay/cancel/{orderId}")
    public String cancelCashPay(@PathVariable String storeSN, @PathVariable Long orderId) {

        orderService.cancelOrderByUser(orderId);
        return "redirect:/%s/cart".formatted(storeSN);
    }

    @GetMapping("/cashpay/status/{orderId}")
    public String getOrderStatus(@PathVariable Long orderId, Model model) {

        Order order = orderService.findById(orderId);
        model.addAttribute("orderId", order.getId());
        model.addAttribute("userNickName", order.getUserNickname());
        model.addAttribute("orderStatus", order.getOrderStatus());
        model.addAttribute("storeSN", order.getStoreSerialNumber());
        return "pay/orderStatusByCashPay";
    }
}
