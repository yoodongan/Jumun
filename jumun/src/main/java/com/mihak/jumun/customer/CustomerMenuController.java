package com.mihak.jumun.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu")
public class CustomerMenuController {

    @GetMapping("")
    public String menuView() {
        return "customer_menu";
    }
}
