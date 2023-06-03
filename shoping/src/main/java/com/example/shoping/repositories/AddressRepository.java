package com.example.shoping.repositories;

import java.util.*;
import com.example.shoping.entities.Address;
import com.example.shoping.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByUser(User user);
}
