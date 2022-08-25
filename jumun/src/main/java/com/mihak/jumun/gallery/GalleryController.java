package com.mihak.jumun.gallery;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class GalleryController {
    private S3Service s3Service;
    private GalleryService galleryService;

    @GetMapping("/gallery")
    public String dispWrite() {

        return "/gallery";
    }

    @PostMapping("/gallery")
    /*form으로부터 넘어온 파일 객체를 받기 위해, MultipartFile 타입의 파라미터를 작성
    S3에 파일 업로드를 할 때 IOException이 발생할 수 있으므로 예외를 던짐.*/
    public String execWrite(GalleryDto galleryDto, MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(file);
        galleryDto.setImgUrl(imgPath);



        galleryService.savePost(galleryDto);

        return "redirect:/gallery";
    }
}
