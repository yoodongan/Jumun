package com.mihak.jumun.customer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /*스프링 시큐리티에서 로그인 요구를 없애기*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll()
//                .and()
//                .csrf().ignoringAntMatchers("/h2-console/**")
//                .and()
//                .headers()
//                .addHeaderWriter(new XFrameOptionsHeaderWriter(
//                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
