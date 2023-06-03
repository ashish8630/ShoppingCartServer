package com.example.shoping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;
    private String fullName;
    private String phoneNumber;
    private String alternatePhoneNumber;
    private String houseNo;
    private String landMark;
    private String pincode;
    private String fullAddress;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user",referencedColumnName = "userId",nullable = false)
    private User user;
    @OneToMany(mappedBy = "address",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Orders> orders =new HashSet<>();

}
