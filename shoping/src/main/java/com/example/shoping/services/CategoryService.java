package com.example.shoping.services;

import com.example.shoping.entities.Categories;
import com.example.shoping.utils.CategoryBody;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public interface CategoryService {

    List<Categories> allCategories();
    Categories getCategoryById(Integer id);

    //Future Works
    Categories createCategory(CategoryBody categoryBody);
    void delete(Integer id);
}
