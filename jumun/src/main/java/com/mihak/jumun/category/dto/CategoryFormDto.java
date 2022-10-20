package com.mihak.jumun.category.dto;

import com.mihak.jumun.owner.entity.Owner;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class CategoryFormDto {

    @NotEmpty(message = "카테고리명은 필수항목입니다.")
    private String name;

    private Owner owner;
}
