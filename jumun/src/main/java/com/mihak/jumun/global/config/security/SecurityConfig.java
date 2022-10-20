package com.mihak.jumun.global.config.security;

import com.mihak.jumun.owner.service.OwnerSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AutoConfigureAfter(SecurityAutoConfiguration.class)
public class SecurityConfig{

    private final OwnerSecurityService ownerSecurityService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()

                .formLogin()
                .loginPage("/admin/login")
                .successHandler(new LoginAuthHandler())

                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .key("key")
                .alwaysRemember(false)
                .tokenValiditySeconds(86400 * 30) // 1달
                .userDetailsService(ownerSecurityService)

                .and()
                .authorizeRequests()
                .mvcMatchers("/","/css/**","/scripts/**","/plugin/**","/fonts/**").permitAll()
                .antMatchers("/admin/login", "/admin/new", "/admin/store", "/admin/store/new").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")


                .and()
                .logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login")
                .deleteCookies("JSESSIONID", "remember-me")
                .invalidateHttpSession(true);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> { web.ignoring().antMatchers("/resources/**"); };
    }
}
