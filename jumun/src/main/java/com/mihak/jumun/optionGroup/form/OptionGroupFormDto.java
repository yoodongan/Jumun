package com.mihak.jumun.optionGroup.form;

import com.mihak.jumun.entity.Store;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class OptionGroupFormDto {
    @NotEmpty(message = "옵션그룹명을 입력해주세요!")
    private String name;

    private Boolean isMultiple;

    private Store store;

}
