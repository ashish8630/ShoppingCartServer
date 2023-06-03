package com.example.shoping.Imple;

import com.example.shoping.dto.CartDto;
import com.example.shoping.dto.ItemsDto;
import com.example.shoping.dto.UserDto;
import com.example.shoping.entities.Cart;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.CartRepository;
import com.example.shoping.repositories.ItemRepository;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.CartService;
import com.example.shoping.utils.CartBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartImple implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Cart createNewCart(CartBody cartBody) {
        User user=this.userRepository.findById(cartBody.getUserId()).orElseThrow();
        Items items=this.itemRepository.findById(cartBody.getItemId()).orElseThrow();
        Cart cart=new Cart();
        cart.setUser(user);
        cart.setItem(items);
        cart.setActive(true);
        cart.setQuantity(cartBody.getQuantity());

        Cart newCart=this.cartRepository.save(cart);
        return newCart;


    }

    @Override
    public List<CartDto> getAllActiveCartOfUser(String userId) {
        User user=this.userRepository.findById(userId).orElseThrow();
        List<Cart> carts=this.cartRepository.getAllActiveCartOfUser(user);

        List<CartDto> cartDtos=carts.stream().map((item)->{
                    CartDto cartdto1=new CartDto();
                    cartdto1.setCartId(item.getCartId());
                    cartdto1.setUser(this.modelMapper.map(item.getUser(), UserDto.class));
                    cartdto1.setItems(this.modelMapper.map(item.getItem(), ItemsDto.class));
                    cartdto1.setActive(item.isActive());
                    cartdto1.setQuantity(item.getQuantity());
                    return cartdto1;
                }
                ).collect(Collectors.toList());
        return cartDtos;

    }

    @Override
    public CartDto updateCartQuantity(Integer cartId,Integer quantity) {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow();
        cart.setQuantity(quantity);
        Cart cart1 = this.cartRepository.save(cart);
        Items items = cart1.getItem();
        CartDto cartDto = new CartDto();
        cartDto.setItems(this.modelMapper.map(items, ItemsDto.class));
        cartDto.setCartId(cart1.getCartId());
        cartDto.setUser(this.modelMapper.map(cart1.getUser(), UserDto.class));
        cartDto.setQuantity(cart1.getQuantity());
        cartDto.setActive(cart.isActive());
        return cartDto;
    }

    @Override
    public CartDto makeCartInactive(Integer cartId) {
        Cart cart=this.cartRepository.findById(cartId).orElseThrow();
        cart.setActive(false);
        Cart cart1=this.cartRepository.save(cart);
        Items items=cart1.getItem();
        CartDto cartDto=new CartDto();
        cartDto.setItems(this.modelMapper.map(items, ItemsDto.class));
        cartDto.setCartId(cart1.getCartId());
        cartDto.setUser(this.modelMapper.map(cart1.getUser(), UserDto.class));
        cartDto.setQuantity(cart1.getQuantity());
        cartDto.setActive(cart.isActive());

        return cartDto;
    }
}
