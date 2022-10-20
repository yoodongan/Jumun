package com.mihak.jumun.store.controller;

import com.mihak.jumun.owner.entity.Owner;
import com.mihak.jumun.store.entity.Store;
import com.mihak.jumun.owner.service.OwnerService;
import com.mihak.jumun.store.service.StoreService;
import com.mihak.jumun.store.dto.CreateFormDto;
import lombok.RequiredArgsConstructor;
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
public class StoreController {

    private final StoreService storeService;
    private final OwnerService ownerService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/admin/store")
    public String redirectStore(Principal principal) {
        Owner owner = ownerService.findById(principal.getName());
        Optional<Store> findStore = storeService.findByOwner(owner);

        if (findStore.isEmpty()) {
            return "redirect:/admin/store/new";
        }
        return "redirect:/%s/admin/store".formatted(findStore.get().getSerialNumber());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{storeSN}/admin/store")
    public String showStoreIndex(@PathVariable(required = false) String storeSN, Model model) {
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

        Owner owner = ownerService.findById(principal.getName());

        Store store = storeService.save(createFormDto, owner);
        return "redirect:/%s/admin/store".formatted(store.getSerialNumber());
    }
}
