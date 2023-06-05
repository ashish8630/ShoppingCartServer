package com.example.shoping.controller;

import com.example.shoping.entities.Address;
import com.example.shoping.services.AddressService;
import com.example.shoping.utils.AddressBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PostMapping("/")
    public ResponseEntity<Address> createAddress(@RequestBody AddressBody addressBody){
        Address address=this.addressService.createNewAddress(addressBody);
        return ResponseEntity.ok(address);
    }
    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable Integer addressId, @RequestBody AddressBody addressBody){
        Address address=this.addressService.updateAddress(addressBody,addressId);
        return ResponseEntity.ok(address);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Address>> getAddressUser(@PathVariable String userId){
        return ResponseEntity.ok(this.addressService.getAllAddressByUser(userId));
    }
    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer addressId){
        this.addressService.DeleteAddress(addressId);
        return ResponseEntity.ok("success");
    }
}
