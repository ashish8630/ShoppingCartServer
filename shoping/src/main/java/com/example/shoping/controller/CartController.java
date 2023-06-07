package com.example.shoping.controller;


import com.example.shoping.entities.Cart;
import com.example.shoping.services.CartService;
import com.example.shoping.utils.CartBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carts")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/")
    public ResponseEntity<Cart> createCart(@RequestBody CartBody cartBody){
        Cart cart=this.cartService.createNewCart(cartBody);
        return ResponseEntity.ok(cart);
    }
    @PutMapping("/id/{cartId}/quantity/{quantity}")
    @CrossOrigin(value = "**")
    public ResponseEntity<Cart> updateCartQuantity(@PathVariable Integer cartId, @PathVariable Integer quantity){
        Cart cart=this.cartService.updateCartQuantity(cartId,quantity);
        return ResponseEntity.ok(cart);
    }
    @PutMapping("/disable/{cartId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<Cart> disableCart(@PathVariable Integer cartId){
        Cart cart=this.cartService.makeCartInactive(cartId);
        return ResponseEntity.ok(cart);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getActiveCartsOfUser(@PathVariable String userId){
        List<Cart> carts=this.cartService.getAllActiveCartOfUser(userId);
        return ResponseEntity.ok(carts);
    }
}
