package com.example.shoping.Imple;


import com.example.shoping.entities.Categories;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.CategoryRepository;
import com.example.shoping.repositories.ItemRepository;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Service
public class ItemImple implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Items createItem(String path, MultipartFile imageFile,String name,String description,Integer stockQuantity,double price,String userId,Integer categoryId) throws FileNotFoundException {
        // File Name
        String filename= imageFile.getOriginalFilename();

        //random name generate
        String randomID = UUID.randomUUID().toString();
        String fileName1=randomID.concat(filename.substring(filename.lastIndexOf(".")));
        System.out.println(fileName1);
        //FullPath
        String filePath = path+ File.separator+fileName1;
        System.out.println(filePath);

        //create folder if not created
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        try {
            Files.copy(imageFile.getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User user=this.userRepository.findById(userId).orElseThrow();
        Categories categories=this.categoryRepository.findById(categoryId).orElseThrow();
        Items items=new Items();
        items.setCategory(categories);
        items.setUser(user);
        items.setDescription(description);
        items.setName(name);
        items.setPrice(price);
        items.setImageUrl("http://localhost:8081/api/items/images/"+fileName1);
        items.setStockQuantity(stockQuantity);
        Items createdItem=this.itemRepository.save(items);


        return createdItem;

    }

    @Override
    public List<Items> getAllItems() {
        List<Items> items = this.itemRepository.findAll();
        return items;
    }

    @Override
    public List<Items> getAllItemsByUser(User user) {
        List<Items> items=this.itemRepository.findByUser(user);
        return items;
    }

    @Override
    public List<Items> getAllItemByCategory(Integer categoryId) {
        Categories categories = this.categoryRepository.findById(categoryId).orElseThrow();
        List<Items> items=this.itemRepository.findByCategory(categories);
        return items;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream is=new FileInputStream(fullPath);
        return is;
    }


// Future Works
    @Override
    public Items updateStock(Integer quantity, Integer itemId) {
        Items items=this.itemRepository.findById(itemId).orElseThrow();
        items.setStockQuantity(quantity);
        Items newItem=this.itemRepository.save(items);
        return newItem;
    }

    @Override
    public Items updateItem(Integer itemId, Items itemsDto) {
        Items items=this.itemRepository.findById(itemId).orElseThrow();
        items.setStockQuantity(itemsDto.getStockQuantity());
        items.setName(itemsDto.getName());
        items.setDescription(itemsDto.getDescription());
        items.setPrice(itemsDto.getPrice());
        Items newItem=this.itemRepository.save(items);
        return newItem;
    }

    @Override
    public void deleteItemById(Integer itemId) {
        Items items=this.itemRepository.findById(itemId).orElseThrow();
        this.itemRepository.delete(items);
    }

    @Override
    public Items getItemById(Integer itemId) {
        Items items=this.itemRepository.findById(itemId).orElseThrow();
        return items;
    }



}
