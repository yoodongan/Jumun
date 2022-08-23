package com.mihak.jumun.customer;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CustomerLogin {
    String nickname;
    private final PasswordEncoder passwordEncoder;
    public CustomerLogin(String nickname) {
        this.nickname = nickname;
        passwordEncoder = null;
    }

    public String createCookie(HttpServletResponse response) {
        //쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
        /*쿠키를 닉네임으로 받지만 이를 해시로 인코딩, 한글 그대로 받으면 문제가 있는 점도 해결*/
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cookie cookie = new Cookie("customerLogin", passwordEncoder.encode(nickname));
        response.addCookie(cookie);
        return "Login Success";
    }
}
