package com.example.shoping.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressBody {
    private String fullName;
    private String phoneNumber;
    private String alternatePhoneNumber;
    private String houseNo;
    private String landMark;
    private String pincode;
    private String fullAddress;
    private String userId;
}
