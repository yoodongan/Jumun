package com.mihak.jumun.pay;

import com.mihak.jumun.cart.CartService;
import com.mihak.jumun.entity.Order;
import com.mihak.jumun.order.OrderService;
import com.mihak.jumun.pay.dto.PaySuccessDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KakaoPayController {

    private final KaKaoPayService kaKaoPayService;
    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping("/kakaopay/{orderId}")
    public String kakaoPay(@PathVariable Long orderId) {

        return "redirect:" + kaKaoPayService.kakaoPayReady(orderId);
    }

    @GetMapping("/kakaoPaySuccess/{orderId}")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model,
                                  @PathVariable Long orderId) {

        Order order = orderService.findOrderById(orderId);
        cartService.changeIsOrdered(order);

        kaKaoPayService.kakaoPayApprove(pg_token, orderId);
        PaySuccessDto paySuccessDto = orderService.getPaySuccessDto(order);
        model.addAttribute("paySuccessDto", paySuccessDto);
        return "pay/kakaoPaySuccess";
    }

    @GetMapping("/{storeSN}/kakaopay/cancel/{orderId}")
    public String kakaoPayCancel(@PathVariable String storeSN, @PathVariable Long orderId,
                              @CookieValue("customerLogin") String customerKey) {

        kaKaoPayService.kakaoPayCancel(orderId);
        orderService.cancelOrderByUser(orderId);
        return "redirect:/" + storeSN + "/cart";
    }

    @GetMapping("/kakaopay/status/{orderId}")
    public String getOrderStatus(@PathVariable Long orderId, Model model) {

        Order order = orderService.findOrderById(orderId);
        model.addAttribute("userNickName", order.getUserNickName());
        model.addAttribute("orderId", order.getId());
        model.addAttribute("orderStatus", order.getOrderStatus());
        model.addAttribute("storeSN", order.getStoreSerialNumber());
        return "pay/orderStatusByKakaoPay";
    }


    @GetMapping("/kakaoPayCancel/{orderId}")
    public String kakaoPayCancelFail(@RequestParam("pg_token") String pg_token, Model model,
                                 @PathVariable Long orderId) {

        orderService.cancelOrderByUser(orderId);
        model.addAttribute("info", kaKaoPayService.kakaoPayApprove(pg_token, orderId));
        return "pay/kakaoPayFail";
    }

    @GetMapping("/kakaoPaySuccessFail/{orderId}")
    public String kakaoPaySuccessFail(@RequestParam("pg_token") String pg_token, Model model,
                                  @PathVariable Long orderId) {

        orderService.cancelOrderByPayFail(orderId);
        model.addAttribute("info", kaKaoPayService.kakaoPayApprove(pg_token, orderId));
        return "pay/kakaoPayFail";
    }
}
