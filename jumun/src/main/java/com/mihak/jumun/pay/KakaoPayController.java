package com.mihak.jumun.pay;

import com.mihak.jumun.cart.CartService;
import com.mihak.jumun.entity.Order;
import com.mihak.jumun.order.OrderService;
import com.mihak.jumun.pay.dto.KakaoPaySuccessDto;
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

        KakaoPaySuccessDto kakaoPaySuccessDto = orderService.getKakaoPaySuccessDto(order);
        model.addAttribute("kakaoPaySuccessDto", kakaoPaySuccessDto);
        return "pay/kakaoPaySuccess";
    }
    @GetMapping("/kakaoPayCancel/{orderId}")
    public String kakaoPayCancel(@RequestParam("pg_token") String pg_token, Model model,
                                  @PathVariable Long orderId) {

        orderService.cancelOrderByUser(orderId);
        model.addAttribute("info", kaKaoPayService.kakaoPayInfo(pg_token, orderId));
        return "pay/kakaoPayFail";
    }

    @GetMapping("/kakaoPaySuccessFail/{orderId}")
    public String kakaoPaySuccessFail(@RequestParam("pg_token") String pg_token, Model model,
                                  @PathVariable Long orderId) {

        orderService.cancelOrderByPayFail(orderId);
        model.addAttribute("info", kaKaoPayService.kakaoPayInfo(pg_token, orderId));
        return "pay/kakaoPayFail";
    }
}
