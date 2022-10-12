package com.mihak.jumun.owner;

import com.mihak.jumun.owner.dto.SignupFormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/login")
    private String showLoginForm() {
        return "store/login_form";
    }

    @GetMapping("/new")
    private String showSignupForm(Model model) {
        model.addAttribute("signupFormDto", new SignupFormDto());
        return "store/signup_form";
    }

    @PostMapping("/new")
    private String doSignup(@Validated @ModelAttribute SignupFormDto signupFormDto,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "store/signup_form";
        }

        if (!signupFormDto.getPassword1().equals(signupFormDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "store/signup_form";
        }

        try {
            ownerService.save(signupFormDto);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("duplicatedOwnerId", "이미 등록된 아이디 입니다");
            return "store/signup_form";
        } catch (Exception e) {
            return "store/signup_form";
        }

        return "redirect:/admin/login";
    }
}