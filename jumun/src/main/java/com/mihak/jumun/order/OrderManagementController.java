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

    //private final OrderManagementService orderManagementService;

    @GetMapping("/{storeSN}/admin/store/order")
    public String orderHome(Model model , @PathVariable String storeSN){
        model.addAttribute("storeSN" , storeSN);
        return "order/order_detail";
    }

    @GetMapping("/{storeSN}/admin/store/order/list")
    public String orderList(Model model , @PathVariable String storeSN){
       // List<Order> orderList = orderManagementService.findbyStoreSN(storeSN);
        model.addAttribute("storeSN" , storeSN);
        return "order/orderList";
    }
}
