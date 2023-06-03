package com.example.shoping.Imple;

import com.example.shoping.entities.Address;
import com.example.shoping.entities.Cart;
import com.example.shoping.entities.Orders;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.*;
import com.example.shoping.services.OrderService;
import com.example.shoping.utils.OrderBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.List;
@Service
public class OrderImple implements OrderService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Orders createOrder(OrderBody orderBody) {
        User user=this.userRepository.findById(orderBody.getUserId()).orElseThrow();
        Address address=this.addressRepository.findById(orderBody.getAddressId()).orElseThrow();
        List<Cart> carts=new ArrayList<>();
        double total=0.0;
        for(Integer id:orderBody.getCartId()){
            Cart cart=this.cartRepository.findById(id).orElseThrow();
            cart.setActive(false);
            this.cartRepository.save(cart);
            total+=(cart.getItem().getPrice()*cart.getQuantity());
            carts.add(cart);
        }
        Orders orders=new Orders();
        orders.setCarts(carts);
        orders.setAddress(address);
        orders.setUser(user);
        orders.setActive(true);
        orders.setDelivery(false);
        orders.setTotal(total);
        Orders orders1=this.ordersRepository.save(orders);
        return orders1;

    }

    @Override
    public List<Orders> getActiveAndUndeliveredOrdersByUser(String userId) {
        User user=this.userRepository.findById(userId).orElseThrow();
        List<Orders> orders=this.ordersRepository.findActiveNotDeliveredOrdersByUser(user);
        return orders;
    }

    @Override
    public Orders cancelOrder(Integer orderId) {
        Orders orders=this.ordersRepository.findById(orderId).orElseThrow();
        orders.setActive(false);
        Orders orders1=this.ordersRepository.save(orders);
        return orders1;
    }

    @Override
    public Orders seDeliveryStatus(Integer orderId) {
        Orders orders=this.ordersRepository.findById(orderId).orElseThrow();
        orders.setDelivery(true);
        Orders orders1=this.ordersRepository.save(orders);
        return orders1;
    }

    @Override
    public List<Orders> getActiveAndDeliveredOrdersByUser(String userId) {
        User user=this.userRepository.findById(userId).orElseThrow();
        List<Orders> orders=this.ordersRepository.findActiveDeliveredOrdersByUser(user);
        return orders;
    }

    @Override
    public List<Orders> getAllActiveOrders() {
        List<Orders> orders=this.ordersRepository.findActiveOrders();
        return orders;
    }
}
