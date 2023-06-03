package com.example.shoping.services;

import com.example.shoping.entities.Orders;
import com.example.shoping.utils.OrderBody;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public interface OrderService {
    Orders createOrder(OrderBody orderBody);
    List<Orders> getActiveAndUndeliveredOrdersByUser(String userId);
    Orders cancelOrder(Integer orderId);
    Orders seDeliveryStatus(Integer orderId);
    List<Orders> getActiveAndDeliveredOrdersByUser(String userId);
    List<Orders> getAllActiveOrders();

}
