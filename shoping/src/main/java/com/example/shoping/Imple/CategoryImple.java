package com.example.shoping.Imple;

import com.example.shoping.entities.Categories;
import com.example.shoping.repositories.CategoryRepository;
import com.example.shoping.services.CategoryService;
import com.example.shoping.utils.CategoryBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryImple implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Categories createCategory(CategoryBody categoryBody) {
        String name=categoryBody.getName();
        Categories categories=new Categories();
        categories.setName(name);
        Categories categories1=this.categoryRepository.save(categories);
        return categories1;
    }

    @Override
    public List<Categories> allCategories() {
        List<Categories> categories=this.categoryRepository.findAll();
        return categories;
    }

    @Override
    public Categories getCategoryById(Integer id) {
        Categories categories=this.categoryRepository.findById(id).orElseThrow();
        return categories;
    }

    @Override
    public void delete(Integer id) {
            this.categoryRepository.deleteById(id);
    }
}
