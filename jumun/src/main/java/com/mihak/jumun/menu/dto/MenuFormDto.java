package com.mihak.jumun.menu.dto;
import com.mihak.jumun.entity.Store;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MenuFormDto {

    @Nullable
    private Long categoryId;

    @NotEmpty(message = "메뉴명을 입력해주세요.")
    private String name;

    @NotNull(message = "가격을 입력해주세요.")
    private Integer price;

    @Nullable
    private String imgUrl;

    @Lob @Nullable
    private String description;

    private Store store;

    private Long optionGroupId;

    public void setMenuInfo(Long categoryId, String menuName, int price, String imgUrl, String description, Store store) {
        this.categoryId = categoryId;
        this.name = menuName;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
        this.store = store;
    }

}
