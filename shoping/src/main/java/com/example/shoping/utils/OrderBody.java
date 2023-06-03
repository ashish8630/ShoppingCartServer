package com.example.shoping.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBody {
    private String userId;
    private List<Integer> cartId=new ArrayList<>();
    private Integer addressId;

}
