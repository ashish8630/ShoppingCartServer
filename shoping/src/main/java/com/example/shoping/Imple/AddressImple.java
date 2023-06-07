package com.example.shoping.Imple;

import java.util.*;
import com.example.shoping.entities.Address;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.AddressRepository;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.AddressService;
import com.example.shoping.utils.AddressBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressImple implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Address createNewAddress(AddressBody addressBody) {
        Address address=new Address();
        User user=this.userRepository.findById(addressBody.getUserId()).orElseThrow();
        address.setUser(user);
        address.setFullAddress(addressBody.getFullAddress());
        address.setHouseNo(addressBody.getHouseNo());
        address.setPincode(addressBody.getPincode());
        address.setLandMark(addressBody.getLandMark());
        address.setPhoneNumber(addressBody.getPhoneNumber());
        address.setAlternatePhoneNumber(addressBody.getAlternatePhoneNumber());
        address.setFullName(addressBody.getFullName());
        Address address1=this.addressRepository.save(address);
        return address1;
    }

    @Override
    public List<Address> getAllAddressByUser(String userId) {
        User user=this.userRepository.findById(userId).orElseThrow();
        List<Address> addresses=this.addressRepository.findByUser(user);
        return addresses;
    }


    //Future Works
    @Override
    public void DeleteAddress(Integer id) {
        Address address=this.addressRepository.findById(id).orElseThrow();
        this.addressRepository.delete(address);
    }

    @Override
    public Address updateAddress(AddressBody addressBody, Integer id) {
        Address address=this.addressRepository.findById(id).orElseThrow();
        address.setFullAddress(addressBody.getFullAddress());
        address.setHouseNo(addressBody.getHouseNo());
        address.setPincode(addressBody.getPincode());
        address.setLandMark(addressBody.getLandMark());
        address.setPhoneNumber(addressBody.getPhoneNumber());
        address.setAlternatePhoneNumber(addressBody.getAlternatePhoneNumber());
        address.setFullName(addressBody.getFullName());
        return this.addressRepository.save(address);

    }
}
