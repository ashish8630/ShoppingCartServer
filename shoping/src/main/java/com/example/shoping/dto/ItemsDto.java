package com.example.shoping.dto;

import com.example.shoping.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDto    {
    private Integer itemId;
    private String name;
    private String description;
    private Integer stockQuantity;
    private UserDto user;
    private String imageUrl;
    private double price;
}

