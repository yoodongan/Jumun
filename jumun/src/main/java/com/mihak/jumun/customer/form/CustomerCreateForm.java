package com.mihak.jumun.customer.form;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CustomerCreateForm {

    @NotEmpty(message = "닉네임은 필수항목입니다.")
    /*Notblank 문자열일 경우 null, "", " "가 입력되었을 때 예외를 발생, NotNull은?*/
    private String nickname;

}
