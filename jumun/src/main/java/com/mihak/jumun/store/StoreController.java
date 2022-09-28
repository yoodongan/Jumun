package com.mihak.jumun.store;

import com.mihak.jumun.entity.Owner;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.owner.OwnerService;
import com.mihak.jumun.store.dto.form.CreateFormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    private final StoreService storeService;
    private final OwnerService ownerService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/admin/store")
    public String afterLoginRouter (Principal principal) {
        Owner owner = ownerService.findByOwnerId(principal.getName());
        Optional<Store> findStore = storeService.findByOwner(owner);

        if (findStore.isEmpty()) {
            return "redirect:/admin/store/new";
        }
        return "redirect:/" + findStore.get().getSerialNumber() + "/admin/store";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store")
    public String showIndex(@PathVariable(required = false) String storeSN, Model model) {
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("store", store);
        return "store/store_index";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/admin/store/new")
    public String showCreateForm(Model model) {
        model.addAttribute("createFormDto", new CreateFormDto());

        return "store/create_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/admin/store/new")
    public String create(@Validated @ModelAttribute CreateFormDto createFormDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "store/create_form";
        }

        Owner owner = ownerService.findByOwnerId(principal.getName());

        Store store = storeService.saveStore(createFormDto, owner);
        return "redirect:/" + store.getSerialNumber() + "/admin/store";
    }
}
