package com.mihak.jumun.mangement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagementController {

    @GetMapping("/{storeSN}/admin/store/order")
    public String orderHome(Model model , @PathVariable String storeSN){
        model.addAttribute("storeSN" , storeSN);
        return "management/managementHome";
    }

    @GetMapping("/{storeSN}/admin/store/order/list")
    public String orderList(Model model , @PathVariable String storeSN){
        model.addAttribute("storeSN" , storeSN);
        return "management/orderList";
    }
}
