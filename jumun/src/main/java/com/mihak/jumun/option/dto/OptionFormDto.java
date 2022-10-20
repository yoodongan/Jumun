package com.mihak.jumun.option.dto;

import com.mihak.jumun.store.entity.Store;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionFormDto {

    @NotEmpty(message = "옵션명을 입력해주세요.")
    private String name;
    @NotNull(message = "가격을 입력해주세요.")
    private int price;

    private Store store;

}
