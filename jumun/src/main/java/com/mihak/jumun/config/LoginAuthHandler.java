package com.mihak.jumun.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginAuthHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("ownerId", authentication.getName());

        log.info("ownerId가 세션에 저장되었습니다. ownerId = {}", httpSession.getAttribute("ownerId"));

//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("storeId")) {
//                log.info("storeId = {}", cookie.getValue());
//            }
//        }

        // 로그인 후 사장님의 가게가 생성되어 있는지 확인 후,
        // 쿠키 저장하고 라다이렉트 하기

        response.sendRedirect("/admin");
    }
}
