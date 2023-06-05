package com.example.shoping.controller;

import java.util.*;

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
    public ResponseEntity<Items> createItem(@RequestBody ItemBody itemBody){
        Items itemsDto1=this.itemService.createItem(itemBody);
        return ResponseEntity.ok(itemsDto1);


    }
    @PutMapping("/stock/{itemId}/quantity/{quantity}")
    public ResponseEntity<Items> updateStockItem(@PathVariable Integer itemId, @PathVariable Integer quantity){
        Items itemsDto1=this.itemService.updateStock(quantity,itemId);
        return ResponseEntity.ok(itemsDto1);
    }
    @PutMapping("/{itemId}")
    public ResponseEntity<Items> updateItem(@PathVariable Integer itemId,@RequestBody Items itemsDto ){
        Items itemsDto1=this.itemService.updateItem(itemId,itemsDto);
        return ResponseEntity.ok(itemsDto1);
    }
    @DeleteMapping("/{itemId}")
    @CrossOrigin(value = "**")
    public ResponseEntity<String> deleteItem(@PathVariable Integer itemId){
        this.itemService.deleteItemById(itemId);
        return ResponseEntity.ok("success");
    }
    @GetMapping("/all")
    public ResponseEntity<List<Items>> getAllItem(){
        List<Items> itemsDtos=this.itemService.getAllItems();
        return ResponseEntity.ok(itemsDtos);
    }
    @GetMapping("/item/{itemId}")
    public Items getByIdItem(@PathVariable Integer itemId){
        Items itemsDto=this.itemService.getItemById(itemId);
        return itemsDto;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Items>> getItemsofUser(@PathVariable String userId){
        User user=this.userRepository.findById(userId).orElseThrow();
        return ResponseEntity.ok(this.itemService.getAllItemsByUser(user));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Items>> getItemsByCategory(@PathVariable Integer categoryId){
        return ResponseEntity.ok(this.itemService.getAllItemByCategory(categoryId));
    }
}
