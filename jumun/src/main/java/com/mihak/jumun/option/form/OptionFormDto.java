package com.mihak.jumun.option.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class OptionFormDto {

    @NotEmpty(message = "옵션명을 입력해주세요.")
    private String name;
    @NotNull(message = "가격을 입력해주세요.")
    private int price;

}
