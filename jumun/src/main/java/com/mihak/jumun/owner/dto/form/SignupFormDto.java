package com.mihak.jumun.owner.dto.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SignupFormDto {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password1;
    @NotEmpty
    private String password2;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String agree;
}