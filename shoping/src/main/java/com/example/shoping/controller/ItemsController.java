package com.example.shoping.controller;

import java.util.*;
import com.example.shoping.dto.ItemsDto;
import com.example.shoping.dto.UserDto;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.ItemService;
import com.example.shoping.utils.ItemBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("api/items")
public class ItemsController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/")
    public ResponseEntity<ItemsDto> createItem(@RequestBody ItemBody itemBody){
        ItemsDto itemsDto1=this.itemService.createItem(itemBody);
        return ResponseEntity.ok(itemsDto1);


    }
    @PutMapping("/stock/{itemId}/quantity/{quantity}")
    public ResponseEntity<ItemsDto> updateStockItem(@PathVariable Integer itemId, @PathVariable Integer quantity){
        ItemsDto itemsDto1=this.itemService.updateStock(quantity,itemId);
        return ResponseEntity.ok(itemsDto1);
    }
    @PutMapping("/{itemId}")
    public ResponseEntity<ItemsDto> updateItem(@PathVariable Integer itemId,@RequestBody ItemsDto itemsDto ){
        ItemsDto itemsDto1=this.itemService.updateItem(itemId,itemsDto);
        return ResponseEntity.ok(itemsDto1);
    }
    @DeleteMapping("/{itemId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<String> deleteItem(@PathVariable Integer itemId){
        this.itemService.deleteItemById(itemId);
        return ResponseEntity.ok("success");
    }
    @GetMapping("/all")
    public ResponseEntity<List<ItemsDto>> getAllItem(){
        List<ItemsDto> itemsDtos=this.itemService.getAllItems();
        return ResponseEntity.ok(itemsDtos);
    }
    @GetMapping("/item/{itemId}")
    public ItemsDto getByIdItem(@PathVariable Integer itemId){
        ItemsDto itemsDto=this.itemService.getItemById(itemId);
        return itemsDto;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ItemsDto>> getItemsofUser(@PathVariable String userId){
        User user=this.userRepository.findById(userId).orElseThrow();
        return ResponseEntity.ok(this.itemService.getAllItemsByUser(user));
    }
}
