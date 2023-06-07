package com.example.shoping.services;



import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import com.example.shoping.utils.ItemBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface ItemService {
    Items createItem(String path, MultipartFile imageFile,String name,String description,Integer stockQuantity,double price,String userId,Integer categoryId) throws FileNotFoundException;
    Items updateStock(Integer quantity,Integer itemId);
    Items updateItem(Integer itemId,Items itemsDto);
    void deleteItemById(Integer itemId);
    Items getItemById(Integer itemId);
    List<Items> getAllItems();
    List<Items> getAllItemsByUser(User user);
    List<Items> getAllItemByCategory(Integer categoryId);

//    String uploadImage(String path, MultipartFile File);

    InputStream getResource(String path,String fileName) throws FileNotFoundException;
}
