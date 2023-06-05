package com.example.shoping.services;



import java.util.*;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import com.example.shoping.utils.ItemBody;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    Items createItem(ItemBody itemBody);
    Items updateStock(Integer quantity,Integer itemId);
    Items updateItem(Integer itemId,Items itemsDto);
    void deleteItemById(Integer itemId);
    Items getItemById(Integer itemId);
    List<Items> getAllItems();
    List<Items> getAllItemsByUser(User user);
    List<Items> getAllItemByCategory(Integer categoryId);
}
