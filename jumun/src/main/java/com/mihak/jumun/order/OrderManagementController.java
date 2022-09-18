package com.mihak.jumun.order;

import com.mihak.jumun.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderManagementController {

    private final OrderManagementService orderManagementService;

    @GetMapping("/{storeSN}/admin/store/list")
    public String orderHome(Model model , @PathVariable String storeSN){
        model.addAttribute("storeSN" , storeSN);
        List<Order> orderList = orderManagementService.findAllByStoreSN(storeSN);
        model.addAttribute("orderList",orderList);
        return "order/orderList";
    }

    @GetMapping("/{storeSN}/admin/store/order/detail/{orderId}")
    public String orderList(Model model , @PathVariable String storeSN,@PathVariable Long orderId){
        Order order = orderManagementService.findbyOrderId(orderId);
        model.addAttribute("order" , order);
        return "order/order_detail";
    }
}
