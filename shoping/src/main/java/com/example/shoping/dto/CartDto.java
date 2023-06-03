package com.example.shoping.dto;

import com.example.shoping.entities.Items;
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
public class CartDto {
    private Integer cartId;
    private UserDto user;
    private ItemsDto items;
    private Integer quantity;
    private boolean isActive=true;
}
