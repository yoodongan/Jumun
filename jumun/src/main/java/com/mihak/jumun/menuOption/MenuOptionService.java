package com.mihak.jumun.menuOption;

import com.mihak.jumun.entity.MenuOption;
import com.mihak.jumun.exception.MenuOptionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
