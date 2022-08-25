package com.mihak.jumun.menu.form;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Store;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MenuForm {

    private int categoryId;

    @NotEmpty(message = "메뉴명을 입력해주세요.")
    private String name;

    @NotNull(message = "가격을 입력해주세요.")
    private Integer price;

    @Nullable
    private String img;   // 이미지 url

    @Lob @Nullable
    private String description;

    @Nullable
    private Store store;

}
