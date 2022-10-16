package com.mihak.jumun.order.controller;

import com.mihak.jumun.cart.dto.CartListDto;
import com.mihak.jumun.order.entity.Order;
import com.mihak.jumun.order.entity.enumuration.OrderStatus;
import com.mihak.jumun.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderManagementController {

    private final OrderService orderService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/order/list")
    public String showOrderList(Model model , @PathVariable String storeSN){
        model.addAttribute("storeSN", storeSN);
        List<Order> orderList = orderService.findAllOrderByStoreSN(storeSN);
        model.addAttribute("orderList", orderList);
        return "order/orderList";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/order/detail/{orderId}")
    public String showOrderDetail(Model model, @PathVariable String storeSN, @PathVariable Long orderId){
        Order order = orderService.findById(orderId);
        CartListDto cartListDto = orderService.getCartListDto(order.getUserNickname());
        model.addAttribute("cartListDto",cartListDto);
        model.addAttribute("order" , order);
        return "order/order_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/order/modify/{orderId}")
    public String modify(@PathVariable String storeSN,@PathVariable Long orderId, OrderStatus orderStatus){
        orderService.changeOrderStatus(orderId, orderStatus);
        return "redirect:/%s/admin/store/order/list".formatted(storeSN);
    }

}
