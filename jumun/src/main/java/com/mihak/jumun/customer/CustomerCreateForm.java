package com.mihak.jumun.customer;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CustomerCreateForm {
    @NotEmpty(message = "닉네임은 필수항목입니다.")
    private String nickname;
}
