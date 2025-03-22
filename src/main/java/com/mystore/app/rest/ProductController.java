package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mystore.app.config.NotFoundException;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
        Product p = productService.addProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product p = productService.getProduct(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
        Product p = productService.updateProduct(id, product);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // TODO: API to search products by name
    @GetMapping("/search/{name}")
    public ResponseEntity<Product> searchByName(@PathVariable("name") String name) throws NotFoundException{
    
     Product p = productService.searchByName(name);
     return ResponseEntity.status(HttpStatus.OK).body(p);
    
    }


    // TODO: API to filter products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> searchByCategory(@PathVariable("category") String category)throws NotFoundException{                                      
     List<Product> listOfProducts = productService.searchByCategory(category);
     return ResponseEntity.status(HttpStatus.OK).body(listOfProducts);
    }


    // TODO: API to filter products by price range
    @GetMapping("/byPrice/{fromPrice}/{toPrice}")
    public ResponseEntity<List<Product>> filterByPriceRange(@PathVariable("fromPrice") Double fromPrice,
     @PathVariable("toPrice") Double toPrice)throws NotFoundException{
        if(toPrice < fromPrice || toPrice < 0 || fromPrice < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else{
        List<Product> listOfProducts = productService.filterByPriceRange(fromPrice,toPrice);
        return ResponseEntity.status(HttpStatus.OK).body(listOfProducts);
        }
     }


    // TODO: API to filter products by stock quantity range
     @GetMapping("/byStockQuantity/{fromRange}/{toRange}")
    public ResponseEntity<List<Product>> filterByStockQuantityRange(@PathVariable("fromRange") Integer fromRange,
     @PathVariable("toRange") Integer toRange)throws NotFoundException{
        if(toRange < fromRange || toRange < 0 || fromRange < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else{
        List<Product> listOfProducts = productService.filterByStockQuantityRange(fromRange,toRange);
        return ResponseEntity.status(HttpStatus.OK).body(listOfProducts);
        }
     }



}
