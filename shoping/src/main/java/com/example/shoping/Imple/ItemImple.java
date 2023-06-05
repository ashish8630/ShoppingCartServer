package com.example.shoping.Imple;


import com.example.shoping.entities.Categories;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.CategoryRepository;
import com.example.shoping.repositories.ItemRepository;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.ItemService;
import com.example.shoping.utils.ItemBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemImple implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Items createItem(ItemBody itemBody) {
        User user=this.userRepository.findById(itemBody.getUserId()).orElseThrow();
        Categories categories=this.categoryRepository.findById(itemBody.getCategoryId()).orElseThrow();
        Items items=new Items();
        items.setCategory(categories);
        items.setUser(user);
        items.setDescription(itemBody.getDescription());
        items.setName(itemBody.getName());
        items.setPrice(itemBody.getPrice());
        items.setImageUrl(itemBody.getImageUrl());
        items.setStockQuantity(itemBody.getStockQuantity());
        Items createdItem=this.itemRepository.save(items);
        return createdItem;

    }

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
}
