package com.mihak.jumun.store.dto.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateFormDto {

    @NotEmpty(message = "관리자님의 아이디는 필수항목입니다.")
    private String name;

    private String zipCode;
    private String streetAdr;
    private String detailAdr;
}
