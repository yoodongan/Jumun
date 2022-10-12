package com.mihak.jumun.customer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.mihak.jumun.customer.dto.CreateFormDto;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequiredArgsConstructor //final을 붙인 건 자동으로 생성자 생성 >>Service
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final StoreService storeService;

    /*사용자에게 보여지는 첫 화면*/
    @GetMapping("/{storeSN}/customer")
    public String showCreateForm(Model model) {
        model.addAttribute("customerCreateForm", new CreateFormDto());
        return "customer/customer_login";
    }


    /*확인버튼을 눌렀을 때 닉네임 중복을 체크*/

    @PostMapping("/{storeSN}/customer")
    private String save(@PathVariable String storeSN, @Valid CreateFormDto createFormDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "customer/customer_login";
        }

        try {
            customerService.save(createFormDto.getNickname());
        }catch(DataIntegrityViolationException e) { //이미 DB에 동일한 닉네임이 있다면 오류 발생
            e.printStackTrace();
            /*reject로는 먹지 않고, rejectValue에 field값을 명시해줘야 먹힌다.*/
            bindingResult.rejectValue("nickname","signupFailed", "이미 등록된 닉네임입니다.");
            return "customer/customer_login";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "customer/customer_login";
        }


        UUID uuid = UUID.randomUUID();
        Cookie cookie = new Cookie("customerLogin", uuid.toString());
        response.addCookie(cookie);

        HttpSession session = request.getSession(true);
        session.setAttribute(uuid.toString(), createFormDto.getNickname());

        Store store = storeService.findBySerialNumber(storeSN);

        return "redirect:/"+store.getSerialNumber()+"/menu";
    }
}
