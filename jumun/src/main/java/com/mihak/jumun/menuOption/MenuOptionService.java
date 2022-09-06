package com.mihak.jumun.menuOption;

import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.entity.MenuMenuOption;
import com.mihak.jumun.entity.MenuOption;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.exception.MenuOptionNotFoundException;
import com.mihak.jumun.menu.MenuRepository;
import com.mihak.jumun.menuOption.form.MenuOptionFormDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuOptionService {

    private final MenuOptionRepository menuOptionRepository;

    public MenuOption findById(Long id) {
        Optional<MenuOption> menuOption = menuOptionRepository.findById(id);

        if (menuOption.isEmpty()) {
            throw new MenuOptionNotFoundException("해당 메뉴 옵션이 존재하지 않습니다.");
        }
        return menuOption.get();
    }

    /*컨트롤러에서 받아오는 인자는 메뉴아이디이므로 이를 가지고 메뉴옵션을 찾아야함.*/
//    public MenuOption findByMenuId(Long id) {
//        MenuMenuOption menuMenuOption = new MenuMenuOption();
//        Long menuOptionId = menuMenuOption.getMenu(new MenuRepository().);
//        Optional<MenuOption> menuOption = menuOptionRepository.findById(menuOptionId);
//
//        if (menuOption.isEmpty()) {
//            throw new MenuOptionNotFoundException("해당 메뉴 옵션이 존재하지 않습니다.");
//        }
//        return menuOption.get();
//    }
    public MenuOption createMenuOption(MenuOptionFormDto menuOptionFormDto, Store store) {
        MenuOption menuOption = MenuOption.builder()
                .name(menuOptionFormDto.getName())
                .price(menuOptionFormDto.getPrice())
                .build();
        menuOption.setStore(store);
        menuOptionRepository.save(menuOption);
        return menuOption;
    }

    public List<MenuOption> findAll() {
        return menuOptionRepository.findAll();
    }

}
