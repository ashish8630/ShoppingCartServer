package com.example.shoping.services;



import java.util.*;
import com.example.shoping.dto.ItemsDto;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import com.example.shoping.utils.ItemBody;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    ItemsDto createItem(ItemBody itemBody);
    ItemsDto updateStock(Integer quantity,Integer itemId);
    ItemsDto updateItem(Integer itemId,ItemsDto itemsDto);
    void deleteItemById(Integer itemId);
    ItemsDto getItemById(Integer itemId);
    List<ItemsDto> getAllItems();
    List<ItemsDto> getAllItemsByUser(User user);
    List<ItemsDto> getAllItemByCategory(Integer categoryId);
}
