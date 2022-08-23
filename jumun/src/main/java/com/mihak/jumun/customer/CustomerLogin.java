package com.mihak.jumun.customer;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CustomerLogin {
    //    public String createCookie(HttpServletResponse response) {
////        logger.info("쿠키 생성");
//        Cookie cookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
//        cookie.setDomain("localhost");
//        cookie.setPath("/");
//        // 30초간 저장
//        cookie.setMaxAge(30*60);
//        cookie.setSecure(true);
//        response.addCookie(cookie);
//
//        return "redirect:/ch05/content";
//    }
    String nickname;

    public CustomerLogin(String nickname) {
        this.nickname = nickname;
    }

    public String createCookie(HttpServletResponse response) {
        //쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)

        Cookie cookie = new Cookie("customerLogin", nickname);
        response.addCookie(cookie);

        return "Login Success";
    }
}
