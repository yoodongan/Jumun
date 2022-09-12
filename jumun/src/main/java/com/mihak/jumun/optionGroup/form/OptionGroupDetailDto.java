package com.mihak.jumun.optionGroup.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OptionGroupDetailDto {
    @NotNull(message = "옵션을 선택해주세요.")
    private Long optionId;

}
