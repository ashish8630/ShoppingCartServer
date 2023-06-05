package com.example.shoping.entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user",referencedColumnName = "userId",nullable = false)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cart> carts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address",referencedColumnName = "addressId",nullable = false)
    private Address address;
    private double total;
    private boolean isActive=true;
    private boolean delivery=false;
    @ElementCollection
    @CollectionTable(name = "cart_value", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "value")
    private List<Integer> cartValue;
    private LocalDate createdDate;
}
