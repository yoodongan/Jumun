package com.mihak.jumun.optionGroup;

import com.mihak.jumun.entity.OptionGroup;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.optionGroup.form.OptionGroupFormDto;
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
public class OptionGroupController {
    private final StoreService storeService;
    private final OptionGroupService optionGroupService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/optionGroup")
    public String optionGroupForm(@PathVariable String storeSN, Model model) {
        model.addAttribute("optionGroupFormDto", new OptionGroupFormDto());
        return "optionGroup/create_optionGroup";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/optionGroup")
    public String createOptionGroup(@PathVariable String storeSN, @Valid OptionGroupFormDto optionGroupFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "optionGroup/create_optionGroup";
        }
        optionGroupFormDto.setStore(storeService.findBySerialNumber(storeSN));
        optionGroupService.createOptionGroup(optionGroupFormDto);
        return "redirect:/%s/admin/store/optionGroup".formatted(storeSN);   // 스토어 관리 페이지로 이동하면 좋을 것 같다.
    }

    /* 옵션 그룹 관리 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/optionGroupList")
    public String manageOptionGroup(@PathVariable String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<OptionGroup> optionGroups = optionGroupService.findAllByStore(store);
        model.addAttribute("optionGroups", optionGroups);
        return "optionGroup/optionGroup_list";
    }

}
