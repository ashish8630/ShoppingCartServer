package com.example.shoping.services;
import java.util.*;
import com.example.shoping.entities.Address;
import com.example.shoping.utils.AddressBody;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    Address createNewAddress(AddressBody addressBody);
    List<Address> getAllAddressByUser(String userId);
    void DeleteAddress(Integer id);
    Address updateAddress(AddressBody addressBody,Integer id);


}
