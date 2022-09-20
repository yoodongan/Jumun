package com.mihak.jumun.storeMgmt;

import com.mihak.jumun.entity.*;
import com.mihak.jumun.order.OrderService;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StoreMgmtController {

    private final StoreService storeService;
    private final OrderService orderService;

    @ModelAttribute("orderStatus")
    public OrderStatus[] orderStatuses() {
        return OrderStatus.values();
    }
    @ModelAttribute("payStatus")
    public PayStatus[] payStatuses() {
        return PayStatus.values();
    }
    @ModelAttribute("payType")
    public PayType[] payTypes() {
        return PayType.values();
    }
    @ModelAttribute("orderType")
    public OrderType[] orderTypes() {
        return OrderType.values();
    }
    /*매장관리 라우팅페이지*/
    @GetMapping("/{storeSN}/admin/store/management")
    public String mgmtHome(Model model , @PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("store", store);
        model.addAttribute("storeSN" , storeSN);
        return "storeMgmt/mgmtHome";
    }

    /*주문내역 페이지*/
    @GetMapping("/{storeSN}/admin/store/management/order")
    public String totalOrderList(Model model , @PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("store", store);
        model.addAttribute("storeSN" , storeSN);
        /*스토어넘버로 모든 주문 내역을 가져온다. order 테이블의 모든 것을 조회하기 위해 객체를 가져옴*/
        List<Order> orderLists = orderService.findAllbyStoreId(storeSN);
        model.addAttribute("orderLists", orderLists);
        return "storeMgmt/orderList";
    }


    /*주문내역 상세 페이지*/
    @GetMapping("/{storeSN}/admin/store/management/order/{orderId}")
    public String totalOrderDetail(Model model , @PathVariable String storeSN, @PathVariable Long orderId){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("store", store);
        model.addAttribute("storeSN" , storeSN);
        /*스토어넘버로 모든 주문 내역을 가져온다. order테이블의 모든 것을 조회하기 위해 객체를 가져옴*/
        Order findOrder = orderService.findOrderById(orderId);
        model.addAttribute("findOrder", findOrder);
        return "storeMgmt/orderDetail";
    }

    /*매출관리 페이지*/
    @GetMapping("/{storeSN}/admin/store/management/revenue")
    public String totalSalesList(Model model , @PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("store", store);
        model.addAttribute("storeSN" , storeSN);
        return "storeMgmt/revenueList";
    }
    /* 메뉴관리 페이지 */
    @GetMapping("/{storeSN}/admin/store/management/menu")
    public String mgmtMenu(Model model , @PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("store", store);
        return "storeMgmt/mgmtMenu";
    }
}
