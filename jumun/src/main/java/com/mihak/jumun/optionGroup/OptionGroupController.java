package com.mihak.jumun.optionGroup;

import com.mihak.jumun.entity.Option;
import com.mihak.jumun.entity.OptionGroup;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.menuAndOptionGroup.MenuAndOptionGroupService;
import com.mihak.jumun.option.OptionService;
import com.mihak.jumun.optionAndOptionGroup.OptionAndOptionGroupService;
import com.mihak.jumun.optionGroup.form.OptionGroupDetailDto;
import com.mihak.jumun.optionGroup.form.OptionGroupFormDto;
import com.mihak.jumun.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OptionGroupController {
    private final StoreService storeService;
    private final OptionGroupService optionGroupService;
    private final OptionService optionService;
    private final OptionAndOptionGroupService optionAndOptionGroupService;
    private final MenuAndOptionGroupService menuAndOptionGroupService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/optionGroup")
    public String showCreateForm(@PathVariable String storeSN, Model model) {
        model.addAttribute("optionGroupFormDto", new OptionGroupFormDto());
        return "optionGroup/create_optionGroup";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/optionGroup")
    public String save(@PathVariable String storeSN, @Valid OptionGroupFormDto optionGroupFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "optionGroup/create_optionGroup";
        }
        optionGroupFormDto.setStore(storeService.findBySerialNumber(storeSN));
        optionGroupService.save(optionGroupFormDto);
        return "redirect:/%s/admin/store/optionGroupList".formatted(storeSN);
    }

    /* 옵션 그룹 관리 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/optionGroupList")
    public String showOptionGroupList(@PathVariable String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        List<OptionGroup> optionGroups = optionGroupService.findAllByStore(store);
        model.addAttribute("optionGroups", optionGroups);
        return "optionGroup/optionGroup_list";
    }

    /* 옵션 그룹 상세 페이지 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/optionGroupDetail/{optionGroupId}")
    public String showOptionGroupDetail(@PathVariable String storeSN,
                                        @PathVariable Long optionGroupId,
                                        Model model) throws Exception {
        Store store = storeService.findBySerialNumber(storeSN);
        OptionGroup optionGroup = optionGroupService.findByIdAndStore(optionGroupId, store);
        model.addAttribute("optionGroupName", optionGroup.getName());
        model.addAttribute("optionGroupId", optionGroupId);
        model.addAttribute("OptionGroupDetailDto", new OptionGroupDetailDto());
        // 해당 스토어 옵션들 드롭다운
        List<Option> options = optionService.findAllByStore(store);
        model.addAttribute("options", options);

        // 옵션그룹에 속하는 옵션들 리스팅 형태로 보여주기.
        List<Option> allByOptionGroup = optionAndOptionGroupService.findAllByOptionGroup(optionGroup);
        model.addAttribute("optionList", allByOptionGroup);

        return "optionGroup/optionGroup_detail";
    }
    // 옵션 그룹 상세페이지에서 옵션 추가하기.
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/optionGroupDetail/{optionGroupId}")
    public String addOption(@PathVariable String storeSN,
                            @PathVariable Long optionGroupId,
                            @ModelAttribute OptionGroupDetailDto optionGroupDetailDto
                                    ) {
        Store store = storeService.findBySerialNumber(storeSN);
        OptionGroup optionGroup = optionGroupService.findByIdAndStore(optionGroupId, store);
        Option option = optionService.findById(optionGroupDetailDto.getOptionId());
        optionAndOptionGroupService.addOption(optionGroup, option);

        return "redirect:/%s/admin/store/optionGroupDetail/%s".formatted(storeSN, optionGroupId);
    }

    // 옵션 그룹 상세페이지에서 옵션 삭제하기.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/optionGroupDetail/delete/{optionGroupId}/{optionId}")
    public String deleteOption(@PathVariable String storeSN,
                               @PathVariable Long optionGroupId,
                               @PathVariable Long optionId,
                               Model model) {
        model.addAttribute("optionId", optionId);
        Option option = optionService.findById(optionId);
        optionAndOptionGroupService.deleteAllByOption(option);
        return "redirect:/%s/admin/store/optionGroupDetail/%s".formatted(storeSN, optionGroupId);
    }


    /* 옵션 그룹 수정 */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/optionGroup/modify/{optionGroupId}")
    public String showModifyForm(@PathVariable String storeSN, @PathVariable Long optionGroupId, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        OptionGroup optionGroup = optionGroupService.findByIdAndStore(optionGroupId, store);
        OptionGroupFormDto optionGroupFormDto = optionGroupService.getOptionGroupFormDtoByOptionGroup(optionGroup);
        model.addAttribute("optionGroupFormDto", optionGroupFormDto);

        return "optionGroup/optionGroup_modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{storeSN}/admin/store/optionGroup/modify/{optionGroupId}")
    public String modify(@PathVariable String storeSN,
                         @PathVariable Long optionGroupId,
                         @Valid OptionGroupFormDto optionGroupFormDto,
                         Model model,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "optionGroup/optionGroup_modify";
        }
        optionGroupService.modify(optionGroupId, optionGroupFormDto);

        return "redirect:/%s/admin/store/optionGroupList".formatted(storeSN, optionGroupId);
    }

    /* 옵션 그룹 삭제하기 */
    @Transactional
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store/optionGroup/delete/{optionGroupId}")
    public String delete(@PathVariable String storeSN,
                         @PathVariable Long optionGroupId) {
        OptionGroup optionGroup = optionGroupService.findByIdAndStore(optionGroupId, storeService.findBySerialNumber(storeSN));
        optionAndOptionGroupService.deleteAllByOptionGroup(optionGroup);
        menuAndOptionGroupService.deleteByOptionGroup(optionGroup);
        optionGroupService.deleteByOptionGroup(optionGroup);

        return "redirect:/%s/admin/store/optionGroupList".formatted(storeSN, optionGroupId);
    }

}
