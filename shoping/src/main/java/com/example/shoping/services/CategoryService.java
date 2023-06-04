package com.example.shoping.services;

import com.example.shoping.entities.Categories;
import com.example.shoping.utils.CategoryBody;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public interface CategoryService {
    Categories createCategory(CategoryBody categoryBody);
    List<Categories> allCategories();
    Categories getCategoryById(Integer id);
    void delete(Integer id);
}
