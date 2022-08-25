package com.mihak.jumun.owner.dto.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SignupFormDto {

    @NotEmpty(message = "관리자님의 아이디는 필수항목입니다.")
    private String loginId;
    @NotEmpty(message = "관리자님의 비밀번호는 필수항목입니다.")
    private String password1;
    @NotEmpty(message = "관리자님의 비밀번호 확인은 필수항목입니다.")
    private String password2;
    @NotEmpty(message = "관리자님의 이름는 필수항목입니다.")
    private String name;
    @NotEmpty(message = "관리자님의 전화번호는 필수항목입니다.")
    private String phoneNumber;
    @NotEmpty(message = "약관 동의는 필수항목입니다.")
    private String agree;
}