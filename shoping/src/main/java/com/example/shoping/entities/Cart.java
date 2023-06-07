package com.example.shoping.entities;


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
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "itemId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Items item;
    private Integer quantity;
    private boolean isActive = true;
    @ManyToOne
    @JsonIgnore
    private Orders order;


}
