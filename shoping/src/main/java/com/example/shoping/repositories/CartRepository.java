package com.example.shoping.repositories;
import java.util.*;
import com.example.shoping.entities.Cart;
import com.example.shoping.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query("SELECT c FROM Cart c WHERE c.user = :user AND c.isActive = true")
    List<Cart> getAllActiveCartOfUser(@Param("user") User user);
}
