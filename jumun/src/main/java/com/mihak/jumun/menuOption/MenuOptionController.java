package com.mihak.jumun.menuOption;

import com.mihak.jumun.entity.MenuOption;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.menuOption.form.MenuOptionFormDto;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuOptionController {
    private final MenuOptionService menuOptionService;
    private final StoreService storeService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{storeSN}/admin/store/option")
    public String createMenuOptionForm(@PathVariable String storeSN, Model model) {
        MenuOptionFormDto menuOptionFormDto = new MenuOptionFormDto();
        model.addAttribute("optionForm", menuOptionFormDto);
        return "option/create_option";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("{storeSN}/admin/store/option")
    public String createMenuOption(@PathVariable String storeSN, @Valid MenuOptionFormDto menuOptionFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "option/create_option";
        }
        Store store = storeService.findBySerialNumber(storeSN);
        MenuOption menuOption = menuOptionService.createMenuOption(menuOptionFormDto, store);

        return "redirect:/%s/admin/store/option".formatted(storeSN);   // 스토어 관리 페이지로 이동하면 좋을 것 같다.
    }

    /* 옵션 관리 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("{storeSN}/admin/store/optionList")
    public String manageOption(Model model) {
        List<MenuOption> menuOptions = menuOptionService.findAll();
        model.addAttribute("menuOptions", menuOptions);
        return "option/option_list";
    }

}
