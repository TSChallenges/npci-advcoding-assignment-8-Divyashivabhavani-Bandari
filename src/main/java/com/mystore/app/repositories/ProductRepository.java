package com.mystore.app.repositories;

import com.mystore.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    // TODO
    Optional<Product> findByName(String name);

    List<Product> findByCategory(String category);
}
