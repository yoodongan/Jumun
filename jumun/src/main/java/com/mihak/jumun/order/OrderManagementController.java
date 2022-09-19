package com.mihak.jumun.order;

import com.mihak.jumun.cart.CartService;
import com.mihak.jumun.cart.dto.CartListDto;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Order;
import com.mihak.jumun.entity.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderManagementController {

    private final OrderManagementService orderManagementService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/order/list")
    public String orderHome(Model model , @PathVariable String storeSN){
        model.addAttribute("storeSN" , storeSN);
        List<Order> orderList = orderManagementService.findAllByStoreSN(storeSN);
        model.addAttribute("orderList",orderList);
        return "order/orderList";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/order/detail/{orderId}")
    public String orderList(Model model , @PathVariable String storeSN,@PathVariable Long orderId){
        Order order = orderManagementService.findbyOrderId(orderId);
        CartListDto cartListDto = orderManagementService.getCartList(order.getUserNickName());
        model.addAttribute("cartListDto",cartListDto);
        model.addAttribute("order" , order);
        return "order/order_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/order/modify/{orderId}")
    public String modifyOrderStatus(Model model , @PathVariable String storeSN,@PathVariable Long orderId
    , OrderStatus orderStatus){
        Order order = orderManagementService.findbyOrderId(orderId);
        order.changeOrderStatus(orderStatus);
        orderManagementService.update(order);
        return "redirect:/%s/admin/store/order/list".formatted(storeSN);
    }

}
