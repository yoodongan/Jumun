package com.mihak.jumun.gallery;

import com.mihak.jumun.entity.Menu;
import lombok.*;

//더이상 쓰지 않는 클래스
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {
    private String imgUrl;

//    public Menu toEntity(){
//        Menu build = Menu.builder()
//                .imgUrl(imgUrl)
//                .build();
//        return build;
//    }

    @Builder
    public GalleryDto(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
