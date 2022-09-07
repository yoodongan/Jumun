package com.mihak.jumun.cart;

import com.mihak.jumun.cart.dto.CartDetailDto;
import com.mihak.jumun.cart.dto.CartDto;
import com.mihak.jumun.cart.dto.CartForm;
import com.mihak.jumun.cartAndOption.CartAndOptionService;
import com.mihak.jumun.entity.Cart;
import com.mihak.jumun.entity.Menu;
import com.mihak.jumun.exception.CartNotFoundException;
import com.mihak.jumun.optionGroup.OptionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartAndOptionService cartMenuOptionService;
    private final OptionGroupService optionGroupService;

    public Cart saveCart(CartForm cartForm, String userNickName, Menu menu) {
        Cart cart = Cart.builder().
                userNickName(userNickName)
                .count(cartForm.getCount())
                .isOrdered(false)
                .menu(menu)
                .build();

        return cartRepository.save(cart);
    }

    public List<CartDto> getCartByUserNickName(String userNickname, boolean isOrdered) {

        List<Cart> carts = cartRepository.findByUserNickNameAndIsOrdered(userNickname, isOrdered);
        List<CartDto> cartDtoList = new ArrayList<>();

        for (Cart cart : carts) {
            CartDto cartDto = CartDto.builder()
                    .id(cart.getId())
                    .menu(cart.getMenu())
                    .count(cart.getCount())
                    .Options(cartMenuOptionService.getOptionsByCart(cart))
                    .build();
            cartDtoList.add(cartDto);
        }
        return cartDtoList;
    }

    public CartDetailDto getCartDetailDtoById(Long id) {
        Optional<Cart> findCart = cartRepository.findById(id);

        if (findCart.isEmpty()) {
            throw new CartNotFoundException("해당 장바구니는 존재하지 않습니다.");
        }

        Cart cart = findCart.get();
        Menu menu = cart.getMenu();
        CartDetailDto cartDetailDto = CartDetailDto.builder()
                .name(menu.getName())
                .imgUrl(menu.getImgUrl())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .checkOptions(cartMenuOptionService.getOptionsByCart(cart))
                .count(cart.getCount())
                .build();

        return cartDetailDto;
    }
}
