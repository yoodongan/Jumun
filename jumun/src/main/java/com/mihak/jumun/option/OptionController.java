package com.mihak.jumun.option;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.option.form.OptionFormDto;
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
public class OptionController {
    private final OptionService optionService;
    private final StoreService storeService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/option")
    public String createOptionForm(@PathVariable String storeSN, Model model) {
        model.addAttribute("optionForm", new OptionFormDto());
        return "option/create_option";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/option")
    public String createOption(@PathVariable String storeSN, @Valid OptionFormDto optionFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "option/create_option";
        }
        Store store = storeService.findBySerialNumber(storeSN);
        optionService.createOption(optionFormDto, store);

        return "redirect:/%s/admin/store/option".formatted(storeSN);   // 스토어 관리 페이지로 이동하면 좋을 것 같다.
    }

    /* 옵션 관리 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/optionList")
    public String manageOption(@PathVariable String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<Option> options = optionService.findAllByStore(store);
        model.addAttribute("storeSN", storeSN);
        model.addAttribute("options", options);
        return "option/option_list";
    }

}
