package com.example.shoping.utils;

import com.example.shoping.entities.Items;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartBody {
    private String userId;
    private Integer itemId;
    private Integer quantity;
}
