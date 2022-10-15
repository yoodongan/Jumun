package com.mihak.jumun.cart;

import com.mihak.jumun.cart.dto.CartDetailDto;
import com.mihak.jumun.cart.dto.CartDto;
import com.mihak.jumun.cart.dto.CartFormDto;
import com.mihak.jumun.cart.dto.CartListDto;
import com.mihak.jumun.cartAndOption.CartAndOptionService;
import com.mihak.jumun.customer.dto.MenuDetailFormDto;
import com.mihak.jumun.entity.*;
import com.mihak.jumun.exception.CartNotFoundException;
import com.mihak.jumun.option.OptionService;
import com.mihak.jumun.optionGroup.OptionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final OptionService optionService;
    private final OptionGroupService optionGroupService;
    private final CartAndOptionService cartAndOptionService;



    public Cart save(CartFormDto cartFormDto, String userNickName, Menu menu) {
        Cart cart = Cart.builder()
                .userNickname(userNickName)
                .count(cartFormDto.getCount())
                .isOrdered(false)
                .menu(menu)
                .build();

        return cartRepository.save(cart);
    }

    public List<CartDto> getCartDtoListByNickname(String nickname, boolean isOrdered) {

        List<Cart> carts = cartRepository.findByUserNicknameAndIsOrdered(nickname, isOrdered);
        List<CartDto> cartDtoList = new ArrayList<>();

        for (Cart cart : carts) {
            CartDto cartDto = CartDto.builder()
                    .cartId(cart.getId())
                    .menu(cart.getMenu())
                    .count(cart.getCount())
                    .options(optionService.getOptionsByCart(cart))
                    .eachMenuTotalPrice(calculateEachMenuTotalPrice(cart))
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
                .optionGroups(optionGroupService.getOptionGroupsByMenu(menu))
                .checkOptions(optionService.getOptionsByCart(cart))
                .count(cart.getCount())
                .build();

        return cartDetailDto;
    }

    public CartFormDto getCartFormById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("해당 장바구니는 존재하지 않습니다."));
        Menu menu = cart.getMenu();

        CartFormDto cartFormDto = CartFormDto.builder()
                .name(menu.getName())
                .imgUrl(menu.getImgUrl())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .count(cart.getCount())
                .optionGroups(optionGroupService.getOptionGroupsByMenu(menu))
                .checkOptions(optionService.getOptionsByCart(cart))
                .build();

        return cartFormDto;
    }

    public CartListDto getCartListByNickname(String nickname) {

        List<CartDto> cartDtos = getCartDtoListByNickname(nickname, false);

        CartListDto cartListDto = CartListDto.builder()
                .cartDtos(cartDtos)
                .orderType(null)
                .totalPrice(calculateTotalPrice(cartDtos))
                .build();

        return cartListDto;
    }

    public CartListDto getCartListForOrder(String nickname) {

        List<CartDto> cartDtos = getCartDtoListByNickname(nickname, true);

        CartListDto cartListDto = CartListDto.builder()
                .cartDtos(cartDtos)
                .orderType(null)
                .totalPrice(calculateTotalPrice(cartDtos))
                .build();

        return cartListDto;
    }

    private int calculateTotalPrice(List<CartDto> cartDtos) {
        int totalPrice = 0;

        for (CartDto cartDto : cartDtos) {
            int price = cartDto.getMenu().getPrice();

            for (Option option : cartDto.getOptions()) {
                price += option.getPrice();
            }
            totalPrice += (price * cartDto.getCount());
        }
        return totalPrice;
    }

    public int calculateEachMenuTotalPrice(Cart cart) {
        int menuTotalPrice = 0;
        int price = cart.getMenu().getPrice();
        // 장바구니_옵션 테이블에서 장바구니를 통해 옵션정보들을 가져온다.
        List<CartAndOption> cartAndOptions = cartAndOptionService.findAllCartAndOptionsByCart(cart);
        for (CartAndOption cartAndOption : cartAndOptions) {
            price += cartAndOption.getOptions().getPrice();
        }
        menuTotalPrice += price;
        return menuTotalPrice;
    }

    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    @Transactional
    public void modify(Long cartId, CartFormDto cartFormDto) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("해당 장바구니는 존재하지 않습니다."));
        cart.setCount(cartFormDto.getCount());

        cartAndOptionService.deleteByCart(cart);
        List<Option> checkOptions = cartFormDto.getCheckOptions();
        List<CartAndOption> cartAndOptions = cartAndOptionService.saveOptions(cart, checkOptions);
        cart.updateCartAndOptions(cartAndOptions);
    }

    public Cart save(MenuDetailFormDto menuDetailFormDto, String userNickname, Menu menu) {
        Cart cart = Cart.builder().
                userNickname(userNickname)
                .count(menuDetailFormDto.getCount())
                .isOrdered(false)
                .menu(menu)
                .build();

        return cartRepository.save(cart);
    }

    @Transactional
    public void cancelOrder(String userNickName) {
        List<Cart> cartList= cartRepository.findByUserNicknameAndIsOrdered(userNickName, true);

        for (Cart cart : cartList) {
            cart.setOrdered(false);
        }
    }

    @Transactional
    public void modifyIsOrdered(Order order) {

        String userNickName = order.getUserNickname();
        List<Cart> carts = cartRepository.findByUserNicknameAndIsOrdered(userNickName, false);

        for (Cart cart : carts) {
            cart.setOrdered(true);
        }
    }
}
