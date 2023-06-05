package com.example.shoping.Imple;


import com.example.shoping.entities.Cart;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.CartRepository;
import com.example.shoping.repositories.ItemRepository;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.CartService;
import com.example.shoping.utils.CartBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartImple implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;

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
    public List<Cart> getAllActiveCartOfUser(String userId) {
        User user=this.userRepository.findById(userId).orElseThrow();
        List<Cart> carts=this.cartRepository.getAllActiveCartOfUser(user);

        return carts;

    }

    @Override
    public Cart updateCartQuantity(Integer cartId,Integer quantity) {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow();
        cart.setQuantity(quantity);
        Cart cart1 = this.cartRepository.save(cart);
        return cart1;
    }

    @Override
    public Cart makeCartInactive(Integer cartId) {
        Cart cart=this.cartRepository.findById(cartId).orElseThrow();
        cart.setActive(false);
        Cart cart1=this.cartRepository.save(cart);
        return cart1;
    }
}
