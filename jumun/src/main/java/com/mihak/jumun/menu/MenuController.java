package com.mihak.jumun.menu;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.menu.form.MenuForm;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.mihak.jumun.gallery.S3Service;
import com.mihak.jumun.exception.MenuNotFoundException;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final CategoryService categoryService;
    private final StoreService storeService;
    /*S3 처리*/
    private final S3Service s3Service;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menu")
    public String menuForm(@PathVariable String storeSN, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menuForm", new MenuForm());
        return "menu/create_menu";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/menu")
    public String create(@PathVariable("storeSN") String storeSN, @Valid MenuForm menuForm, BindingResult result, MultipartFile file) throws IOException {
        // 메뉴명 Null 값, 가격 Null 값 예외 체크
        if (result.hasErrors()) {
            return "menu/create_menu";
        }
        // 메뉴명 중복 체크.
        Optional<Menu> oMenu = menuService.findByName(menuForm.getName());
        if (oMenu.isPresent()) {
            result.rejectValue("name", "duplicatedMenu", "이미 똑같은 메뉴가 있습니다.");
            return "menu/create_menu";
        }


        Store store = storeService.findBySerialNumber(storeSN);



        /*S3 컨트롤러 부분*/
        String imgPath = s3Service.upload(file);

//        galleryDto.setImgUrl(imgPath);
//        DTO에서 하는 역할인 Menu에 url 저장을 함께 합치도록 함.
//        galleryService.savePost(galleryDto);

        /*menuForm의 변수에 S3처리 후 리턴된 Url을 넣어주는 코드*/
        menuForm.setImgUrl(imgPath);

        menuForm.setStore(store);
        menuService.saveMenu(menuForm);



        return "redirect:/" + store.getSerialNumber() + "/admin/store/menuList";
    }

    // 관리자 메뉴 화면 보여주기 .
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menuList")
    public String menuList(@PathVariable String storeSN, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findAll();
        model.addAttribute("menuList", menuList);
        model.addAttribute("storeSN", storeSN);

        return "menu/menu_list";
    }

    /* 메뉴 수정 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menu/modify/{menuId}")
    public String modifyMenuForm(@PathVariable String storeSN, @PathVariable Long menuId, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        Optional<Menu> findMenu = menuService.findById(menuId);
        if(!(findMenu.isPresent())) throw new MenuNotFoundException("수정할 메뉴가 없습니다!");
        MenuForm menuForm = new MenuForm();
        Menu menu = findMenu.get();
        Category category = menu.getCategory();

        menuForm.setMenuInfo(category.getId(), menu.getName(), menu.getPrice(), menu.getImgUrl(), menu.getDescription(), menu.getStore());

        model.addAttribute("menuForm", menuForm);
        return "menu/modify_menu";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/menu/modify/{menuId}")
    public String modifyMenu(@PathVariable("storeSN") String storeSN, @PathVariable Long menuId, @Valid MenuForm menuForm, BindingResult result) {
        // 메뉴명 Null 값, 가격 Null 값 예외 체크
        if (result.hasErrors()) {
            return "menu/modify_menu";
        }
        // 메뉴명 중복 체크.
        Optional<Menu> oMenu = menuService.findByName(menuForm.getName());
        if (oMenu.isPresent() && oMenu.get().getId() != menuId) {
            result.rejectValue("name", "duplicatedMenu", "똑같은 메뉴명이 이미 존재합니다!");
            return "menu/modify_menu";
        }
        Store store = storeService.findBySerialNumber(storeSN);
        menuForm.setStore(store);
        menuService.changeMenu(menuId, menuForm);
        return "redirect:/" + store.getSerialNumber() + "/admin/store/menuList";

    }

    /* 메뉴 삭제 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menu/delete/{menuId}")
    public String deleteMenu(@PathVariable("storeSN") String storeSN, @PathVariable Long menuId) {
        Menu menu = menuService.findById(menuId).get();
        Store store = menu.getStore();
        menuService.remove(menu);

        return "redirect:/" + store.getSerialNumber() + "/admin/store/menuList";
    }



}