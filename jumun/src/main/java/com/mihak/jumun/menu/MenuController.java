package com.mihak.jumun.menu;

import com.mihak.jumun.category.CategoryService;
import com.mihak.jumun.entity.Category;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.gallery.S3Service;
import com.mihak.jumun.menu.form.MenuForm;
import com.mihak.jumun.store.StoreService;
import com.mihak.jumun.storeCategory.SCService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final CategoryService categoryService;
    private final StoreService storeService;
    private final SCService scService;
    /*S3 처리*/
    private final S3Service s3Service;

    /* 메뉴 생성폼 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menu")
    public String menuForm(@PathVariable String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menuForm", new MenuForm());
        return "menu/create_menu";
    }
    /* 메뉴 생성 */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/menu")
    public String create(@PathVariable("storeSN") String storeSN, @Valid MenuForm menuForm, BindingResult result, MultipartFile file) throws IOException {
        // 메뉴명 Null 값, 가격 Null 값 예외 체크
        if (result.hasErrors()) {
            return "menu/create_menu";
        }
        // 메뉴명 중복 체크.
        Store store = storeService.findBySerialNumber(storeSN);
        menuForm.setStore(store);
        // 여기다가 중복 로직 추가.
        boolean isMenuDuplicated = menuService.isMenuDuplicated(menuForm.getName(), menuForm.getStore());
        if (isMenuDuplicated) {
            result.rejectValue("name", "duplicatedMenu", "이미 똑같은 메뉴가 있습니다.");
            return "menu/create_menu";
        }
        if (!file.isEmpty()) {
            /*S3 컨트롤러 부분*/
            String imgPath = s3Service.upload(file);
            /*menuForm의 변수에 S3처리 후 리턴된 Url을 넣어주는 코드*/
            menuForm.setImgUrl(imgPath);
        }else
            menuForm.setImgUrl("https://via.placeholder.com/100x100.png?text=No Image");

        menuService.saveMenu(menuForm);
        return "redirect:/" + store.getSerialNumber() + "/admin/store/menuList";
    }

    // 관리자 메뉴 화면 보여주기 .
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menuList")
    public String menuList(@PathVariable String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findAllByStore(storeSN);
        model.addAttribute("menuList", menuList);
        model.addAttribute("storeSN", storeSN);

        return "menu/menu_list";
    }
    /*기본, 관리자가 카테고리를 눌렀을 때*/
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/menuList")
    @ResponseBody
    public String changeMenuViewByCategory(@RequestParam Long categoryId, @PathVariable("storeSN") String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findByCategoryId(categoryId);
        model.addAttribute("menuList" , menuList);
        model.addAttribute("storeSN", storeSN);

        return "redirect:/" +storeSN+ "/admin/store/menu_list"+categoryId;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menuList/{categoryId}")
    public String menuView(@PathVariable("storeSN") String storeSN, @PathVariable("categoryId") Long categoryId, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findByCategoryId(categoryId);
        model.addAttribute("categoryId" , categoryId);
        model.addAttribute("menuList" , menuList);
        model.addAttribute("storeSN", storeSN);

        return "menu/menu_list";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/menuList/{categoryId}")
    @ResponseBody
    public String changeMenuViewByAnotherCategory(@PathVariable("categoryId") Long categoryId, @PathVariable("storeSN") String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        List<Menu> menuList = menuService.findByCategoryId(categoryId);
        model.addAttribute("menuList" , menuList);
        model.addAttribute("storeSN", storeSN);

        return "redirect:/" +storeSN+ "/admin/store/menu_list"+categoryId;
    }

    /* 메뉴 수정 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menu/modify/{menuId}")
    public String modifyMenuForm(@PathVariable String storeSN, @PathVariable Long menuId, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Category> categoryList = scService.findAllbyStoreId(store.getId());
        model.addAttribute("categoryList", categoryList);
        Menu findMenu = menuService.findById(menuId);
        MenuForm menuForm = new MenuForm();
        Category category = findMenu.getCategory();

        menuForm.setMenuInfo(category.getId(), findMenu.getName(), findMenu.getPrice(), findMenu.getImgUrl(), findMenu.getDescription(), findMenu.getStore());

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
        Store store = storeService.findBySerialNumber(storeSN);
        menuForm.setStore(store);
        boolean isMenuDuplicatedAndDifferentId = menuService.isMenuDuplicatedAndDifferentId(menuForm.getName(), menuForm.getStore(), menuId);
        if (isMenuDuplicatedAndDifferentId) {
            result.rejectValue("name", "duplicatedMenu", "이미 똑같은 메뉴가 있습니다.");
            return "menu/modify_menu";
        }

        menuService.changeMenu(menuId, menuForm);
        return "redirect:/" + store.getSerialNumber() + "/admin/store/menuList";

    }

    /* 메뉴 삭제 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/menu/delete/{menuId}")
    public String deleteMenu(@PathVariable("storeSN") String storeSN, @PathVariable Long menuId) {
        Menu menu = menuService.findById(menuId);
        Store store = menu.getStore();
        menuService.remove(menu);

        return "redirect:/" + store.getSerialNumber() + "/admin/store/menuList";
    }

}