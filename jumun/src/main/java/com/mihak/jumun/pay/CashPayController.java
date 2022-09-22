package com.mihak.jumun.pay;

import com.mihak.jumun.cart.CartService;
import com.mihak.jumun.entity.Order;
import com.mihak.jumun.order.OrderService;
import com.mihak.jumun.pay.dto.PaySuccessDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CashPayController {

    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping("/cashPaySuccess/{orderId}")
    public String cashPaySuccess(@PathVariable Long orderId, Model model) {

        Order order = orderService.findOrderById(orderId);
        cartService.changeIsOrdered(order);

        PaySuccessDto paySuccessDto = orderService.getPaySuccessDto(order);
        model.addAttribute("paySuccessDto", paySuccessDto);
        return "pay/cashPaySuccess";
    }

    @GetMapping("/{storeSN}/cashpay/cancel/{orderId}")
    public String kakaoPayCancel(@PathVariable String storeSN, @PathVariable Long orderId,
                                 @CookieValue("customerLogin") String customerKey) {

        orderService.cancelOrderByUser(orderId);
        return "redirect:/" + storeSN + "/cart";
    }

    @GetMapping("/cashpay/status/{orderId}")
    public String getOrderStatus(@PathVariable Long orderId, Model model) {

        Order order = orderService.findOrderById(orderId);
        model.addAttribute("orderId", order.getId());
        model.addAttribute("orderStatus", order.getOrderStatus());
        model.addAttribute("storeSN", order.getStoreSerialNumber());
        return "pay/orderStatusByCashPay";
    }
}
