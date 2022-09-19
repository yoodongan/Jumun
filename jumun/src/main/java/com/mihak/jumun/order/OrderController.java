package com.mihak.jumun.order;

import com.mihak.jumun.cart.CartService;
import com.mihak.jumun.cart.dto.CartDto;
import com.mihak.jumun.entity.Order;
import com.mihak.jumun.entity.OrderStatus;
import com.mihak.jumun.entity.PayType;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.order.dao.OrderDao;
import com.mihak.jumun.order.dto.OrderDtoFromCart;
import com.mihak.jumun.order.dto.OrderFormDto;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StoreService storeService;
    private final CartService cartService;
    private final OrderDao orderDao;

    @PostMapping("/{storeSN}/order")
    public String order(@PathVariable String storeSN,
                        HttpServletRequest request, @CookieValue("customerLogin") String customerKey,
                        @ModelAttribute OrderDtoFromCart orderDtoFromCart) {
        HttpSession session = request.getSession(true);
        String userNickname = session.getAttribute(customerKey).toString();

        orderDtoFromCart.setUserNickName(userNickname);
        orderDtoFromCart.setStoreSerialNumber(storeSN);

        orderDao.addOrderDtoFromCart(orderDtoFromCart, userNickname);
        return "redirect:/" + storeSN + "/pay";
    }

    @ModelAttribute("payTypes")
    public PayType[] payTypes() {
        return PayType.values();
    }

    @GetMapping("/{storeSN}/pay")
    public String showOrder(@PathVariable String storeSN,
                            HttpServletRequest request, @CookieValue("customerLogin") String customerKey,
                            Model model) {

        HttpSession session = request.getSession(true);
        String userNickname = session.getAttribute(customerKey).toString();
        Store store = storeService.findBySerialNumber(storeSN);

        OrderDtoFromCart dto = orderDao.getOrderDtoFromCart(userNickname);

        model.addAttribute("orderFormDto", new OrderFormDto());
        model.addAttribute("storeName", store.getName());
        model.addAttribute("totalPrice", dto.getTotalPrice());

        return "order/orderForm";
    }

    @PostMapping("/{storeSN}/pay")
    public String doOrder(@PathVariable String storeSN, HttpServletRequest request,
                          @CookieValue("customerLogin") String customerKey, @ModelAttribute OrderFormDto orderFormDto) {

        HttpSession session = request.getSession(true);
        String userNickname = session.getAttribute(customerKey).toString();

        OrderDtoFromCart orderDtoFromCart = orderDao.getOrderDtoFromCart(userNickname);
        Order order = orderService.saveOrder(orderDtoFromCart, orderFormDto);

        if (true) { // 간편 결제 성공
            order.setOrderStatus(OrderStatus.BEFOREORDER);
            return "redirect:/" + storeSN + "/order/" + order.getId();
        } else { // 간편 결제 실패
            orderService.cancelOrderByPayFail(order.getId());
            return "order/order_failed";
        }
    }

    @GetMapping("/{storeSN}/order/{orderId}")
    public String showOrderStatus(@PathVariable String storeSN, @PathVariable Long orderId,
                                  HttpServletRequest request, Model model,
                                  @CookieValue("customerLogin") String customerKey, @ModelAttribute OrderFormDto orderFormDto) {

        HttpSession session = request.getSession(true);
        String userNickname = session.getAttribute(customerKey).toString();

        Order order = orderService.findOrderById(orderId);
        List<CartDto> orderHistory = cartService.getCartByUserNickName(order.getUserNickName(), true);
        model.addAttribute("userNickName", userNickname);
        model.addAttribute("orderId", order.getId());
        model.addAttribute("orderHistory", orderHistory);
        model.addAttribute("orderStatus", order.getOrderStatus());
        model.addAttribute("orderTotalPrice", order.getTotalPrice());

        return "order/order_status";
    }

    @GetMapping("/{storeSN}/order/cancel/{orderId}")
    public String cancelOrder(@PathVariable String storeSN, @PathVariable Long orderId,
                              @CookieValue("customerLogin") String customerKey) {

        orderService.cancelOrderByUser(orderId);
        return "redirect:/{storeSN}/cart";
    }
}
