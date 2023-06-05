package com.example.shoping.services;
import java.util.*;

import com.example.shoping.entities.Cart;
import com.example.shoping.utils.CartBody;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart createNewCart(CartBody cartBody);
    List<Cart> getAllActiveCartOfUser(String userId);
    Cart updateCartQuantity(Integer cartId,Integer quantity);
    Cart makeCartInactive(Integer cartId);

}
