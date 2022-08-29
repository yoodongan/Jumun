package com.mihak.jumun.gallery;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//더이상 쓰지 않는 클래스
/*DB에 저장하는 로직*/
@Service
@AllArgsConstructor
public class GalleryService {
    private GalleryRepository galleryRepository;

//    public void savePost(GalleryDto galleryDto) {
//        galleryRepository.save(galleryDto.toEntity());
//    }
}
