package com.example.shoping.Imple;

import com.example.shoping.dto.ItemsDto;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
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
    @Override
    public ItemsDto createItem(ItemBody itemBody) {
        User user=this.userRepository.findById(itemBody.getUserId()).orElseThrow();
        Items items=new Items();
        items.setUser(user);
        items.setDescription(itemBody.getDescription());
        items.setName(itemBody.getName());
        items.setPrice(itemBody.getPrice());
        items.setImageUrl(itemBody.getImageUrl());
        items.setStockQuantity(itemBody.getStockQuantity());
        Items createdItem=this.itemRepository.save(items);
        return this.modelMapper.map(createdItem,ItemsDto.class);

    }

    @Override
    public ItemsDto updateStock(Integer quantity, Integer itemId) {
        Items items=this.itemRepository.findById(itemId).orElseThrow();
        items.setStockQuantity(quantity);
        Items newItem=this.itemRepository.save(items);
        return this.modelMapper.map(newItem,ItemsDto.class);
    }

    @Override
    public ItemsDto updateItem(Integer itemId, ItemsDto itemsDto) {
        Items items=this.itemRepository.findById(itemId).orElseThrow();
        items.setStockQuantity(itemsDto.getStockQuantity());
        items.setName(itemsDto.getName());
        items.setDescription(itemsDto.getDescription());
        items.setPrice(itemsDto.getPrice());
        Items newItem=this.itemRepository.save(items);
        return this.modelMapper.map(newItem,ItemsDto.class);
    }

    @Override
    public void deleteItemById(Integer itemId) {
        Items items=this.itemRepository.findById(itemId).orElseThrow();
        this.itemRepository.delete(items);
    }

    @Override
    public ItemsDto getItemById(Integer itemId) {
        Items items=this.itemRepository.findById(itemId).orElseThrow();
        ItemsDto itemsDtos=this.modelMapper.map(items,ItemsDto.class);
        return itemsDtos;
    }

    @Override
    public List<ItemsDto> getAllItems() {
        List<Items> items = this.itemRepository.findAll();
        List<ItemsDto> itemsDtos=items.stream().map((item)->this.modelMapper.map(item,ItemsDto.class)).collect(Collectors.toList());
        return itemsDtos;
    }

    @Override
    public List<ItemsDto> getAllItemsByUser(User user) {
        List<Items> items=this.itemRepository.findByUser(user);
        List<ItemsDto> itemsDtos=items.stream().map((item)->this.modelMapper.map(item,ItemsDto.class)).collect(Collectors.toList());

        return itemsDtos;
    }
}
