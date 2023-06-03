package com.example.shoping.services;
import java.util.*;

import com.example.shoping.dto.CartDto;
import com.example.shoping.entities.Cart;
import com.example.shoping.utils.CartBody;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart createNewCart(CartBody cartBody);
    List<CartDto> getAllActiveCartOfUser(String userId);
    CartDto updateCartQuantity(Integer cartId,Integer quantity);
    CartDto makeCartInactive(Integer cartId);

}
