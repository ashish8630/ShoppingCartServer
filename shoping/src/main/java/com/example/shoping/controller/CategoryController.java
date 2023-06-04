package com.example.shoping.controller;


import com.example.shoping.entities.Categories;
import com.example.shoping.services.CategoryService;
import com.example.shoping.utils.CategoryBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<Categories> createNewCategory(@RequestBody CategoryBody categoryBody){
        Categories categories=this.categoryService.createCategory(categoryBody);
        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<Categories>> getAllCategory(){
        List<Categories> categories=this.categoryService.allCategories();
        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Integer id){
        Categories categories=this.categoryService.getCategoryById(id);
        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id){
        this.categoryService.delete(id);
    }


}
