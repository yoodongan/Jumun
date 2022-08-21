package com.mihak.jumun.customer;


import com.mihak.jumun.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor //final을 붙인 건 자동으로 생성자 생성 >>Service
public class CustomerController {

    private final CustomerService customerService;

    /*사용자에게 보여지는 첫 화면*/
    @GetMapping("")
    public String signup(Model model) {
        model.addAttribute("customerCreateForm", new CustomerCreateForm());
        return "customer_login";
    }

    /*확인버튼을 눌렀을 때 닉네임 중복을 체크*/
    @PostMapping("")
    private String signup(@Valid CustomerCreateForm customerCreateForm) {

//        Customer customerInfo = this.customerService.signup();
//        model.addAttribute("customerInfo", customerInfo);
        customerService.create(customerCreateForm.getNickname());
        return "redirect:/menu";
    }
}
