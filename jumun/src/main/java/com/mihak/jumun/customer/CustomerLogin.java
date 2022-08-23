package com.mihak.jumun.customer;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CustomerLogin {
    public String createCookie(HttpServletResponse response) {
        //쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)

        /*쿠키를 닉네임으로 받지만 이를 해시로 인코딩, 한글 그대로 받으면 문제가 있는 점도 해결*/
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        /*패스워드 인코딩 방식 대신 UUID를 넣어서 해결*/
        UUID cookieValue = UUID.randomUUID();
        Cookie cookie = new Cookie("customerLogin", cookieValue.toString());
        response.addCookie(cookie);
        return "Login Success";
    }
    /*쿠키 여부를 묻는다.*/
    /*필요없을 것 같아서 안넣었습니다.*/
//    public String checkCookie(HttpServletRequest request){
//        /*만약 쿠키가 존재한다면 /menu로 리다이렉트*/
//        Cookie[] cookies = request.getCookies();
//
//        if(cookies != null) {
//            for(Cookie cookie : cookies) {
//                System.out.println(cookie);
//                if("customerLogin".equals(cookie.getName()) ){
//                    return "redirect:/menu";                }
//            }
//        }
//        return "Check Success";
//    }
}
