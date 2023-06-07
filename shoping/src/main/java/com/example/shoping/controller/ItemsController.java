package com.example.shoping.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/items")
public class ItemsController {
    @Autowired
    private ItemService itemService;

    @Value("${project.image}")
    private String path;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/")
    public ResponseEntity<Items> createItem(@RequestParam("file") MultipartFile image ,
                                            @RequestParam("name") String name,
                                            @RequestParam("description") String description,
                                            @RequestParam("stockQuantity") Integer stockQuantity,
                                            @RequestParam("price") double price,
                                            @RequestParam("userId") String userId,
                                            @RequestParam("categoryId") Integer categoryId
                                            ) throws FileNotFoundException {

        Items itemsDto1 = this.itemService.createItem(path,image,name,description,stockQuantity,price,userId,categoryId);
        return ResponseEntity.ok(itemsDto1);
    }


        @GetMapping(value = "/images/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
        public void downloadImage(@PathVariable("imageName") String imageName,
                                  HttpServletResponse response
        ) throws IOException {
            InputStream resource = this .itemService.getResource(path,imageName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(resource,response.getOutputStream());
        }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Items>> getItemsofUser(@PathVariable String userId){
        User user=this.userRepository.findById(userId).orElseThrow();
        return ResponseEntity.ok(this.itemService.getAllItemsByUser(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Items>> getAllItem(){
        List<Items> itemsDtos=this.itemService.getAllItems();
        return ResponseEntity.ok(itemsDtos);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Items>> getItemsByCategory(@PathVariable Integer categoryId){
        return ResponseEntity.ok(this.itemService.getAllItemByCategory(categoryId));
    }


    //Future Works

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

    @GetMapping("/item/{itemId}")
    public Items getByIdItem(@PathVariable Integer itemId){
        Items itemsDto=this.itemService.getItemById(itemId);
        return itemsDto;
    }


}
