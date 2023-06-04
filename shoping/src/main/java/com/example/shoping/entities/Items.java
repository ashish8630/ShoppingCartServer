package com.example.shoping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;
    private String name;
    private String description;
    private Integer stockQuantity;
    @Column(length = 10000)
    private String imageUrl;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId",nullable = false)
    private Categories category;
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Cart> cart=new HashSet<>();
}
