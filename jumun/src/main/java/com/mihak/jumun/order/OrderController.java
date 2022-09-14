package com.mihak.jumun.order;

import com.mihak.jumun.cart.dto.CartFormDto;
import com.mihak.jumun.entity.Order;
import com.mihak.jumun.entity.OrderType;
import com.mihak.jumun.entity.PayType;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.order.dto.OrderDtoFromCart;
import com.mihak.jumun.order.dto.OrderFormDto;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StoreService storeService;

    @PostMapping("/{storeSN}/order")
    public String order(@PathVariable String storeSN,
                        HttpServletRequest request, @CookieValue("customerLogin") String customerKey,
                        @ModelAttribute OrderDtoFromCart orderDtoFromCart) {
        HttpSession session = request.getSession(true);
        String userNickname = session.getAttribute(customerKey).toString();

        orderDtoFromCart.setUserNickName(userNickname);
        orderDtoFromCart.setStoreSerialNumber(storeSN);

        Order order = orderService.saveOrderWithOrderDto(orderDtoFromCart);
        return "redirect:/" + storeSN + "/order/" + order.getId();
    }

    @ModelAttribute("payTypes")
    public PayType[] payTypes() {
        return PayType.values();
    }

    @GetMapping("/{storeSN}/order/{orderId}")
    public String showOrder(@PathVariable String storeSN, @PathVariable Long orderId,
                            HttpServletRequest request, @CookieValue("customerLogin") String customerKey,
                            Model model) {

        Store store = storeService.findBySerialNumber(storeSN);
        Order order = orderService.findOrderById(orderId);
        int totalPrice = order.getTotalPrice();

        model.addAttribute("orderFormDto", new OrderFormDto());
        model.addAttribute("storeName", store.getName());
        model.addAttribute("totalPrice", totalPrice);
        return "order/orderForm";
    }

    @PostMapping("/{storeSN}/order/{orderId}")
    public String doOrder(@PathVariable String storeSN, @PathVariable Long orderId,
                            HttpServletRequest request, @CookieValue("customerLogin") String customerKey,
                            @ModelAttribute OrderFormDto orderFormDto) {

        orderService.setOrderAboutPay(orderId, orderFormDto);
        return "order/orderForm"; // 간편결제!
    }

}
