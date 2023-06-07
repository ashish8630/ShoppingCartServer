package com.example.shoping.utils;

import com.example.shoping.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemBody {
    private String name;
    private String description;
    private Integer stockQuantity;
    private double price;
    private String userId;
    private Integer categoryId;
    private String fileName;
    private InputStream is;
}
