package com.example.shoping.repositories;

import com.example.shoping.entities.Orders;
import com.example.shoping.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface OrdersRepository  extends JpaRepository<Orders,Integer> {
    @Query("SELECT o FROM Orders o WHERE o.isActive = true AND o.delivery = false AND o.user = :user")
    List<Orders> findActiveNotDeliveredOrdersByUser(User user);
    @Query("SELECT o FROM Orders o WHERE o.isActive = true AND o.delivery = true AND o.user = :user")
    List<Orders> findActiveDeliveredOrdersByUser(User user);
    @Query("SELECT o FROM Orders o WHERE o.isActive = true")
    List<Orders> findActiveOrders();
}
