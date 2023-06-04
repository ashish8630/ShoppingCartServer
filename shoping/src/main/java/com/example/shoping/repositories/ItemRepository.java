package com.example.shoping.repositories;

import java.util.*;

import com.example.shoping.entities.Categories;
import com.example.shoping.entities.Items;
import com.example.shoping.entities.User;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items,Integer> {

    List<Items> findByUser(User user);
    List<Items> findByCategory(Categories categories);
}
