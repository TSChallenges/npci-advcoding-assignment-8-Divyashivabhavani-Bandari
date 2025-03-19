package com.mystore.app.repositories;

import com.mystore.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    // TODO
    Optional<Product> findByName(String name);
}
