package com.mystore.app.service;

import com.mystore.app.entity.Product;
import com.mystore.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import com.mystore.app.config.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;






import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private Integer currentId = 1;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setId(currentId++);
        productRepository.save(product);
        return product;
    }

    public Page<Product> getAllProducts(int page,int size,String sortBy) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(sortBy));
        return productRepository.findAll(pageable);
    }

    public Product getProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.get();
    }

    public Product updateProduct(Integer id, Product product) {
        Product p = productRepository.findById(id).get();
        if (p == null) return null;
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        p.setStockQuantity(product.getStockQuantity());
        productRepository.save(p);
        return p;
    }

    public String deleteProduct(Integer id) {
        Product p = productRepository.findById(id).get();
        if (p == null) return "Product Not Found";
        productRepository.delete(p);
        return "Product Deleted Successfully";
    }

    // TODO: Method to search products by name
    public Product searchByName(String name) throws NotFoundException{
            return productRepository.findByName(name)
            .orElseThrow(() -> new NotFoundException("404", "Product Not found by name: " + name));
    }


    // TODO: Method to filter products by category
    public List<Product> searchByCategory(String category) throws NotFoundException{
        List<Product> listOfProducts = productRepository.findByCategory(category);
        if(listOfProducts.isEmpty()){
            throw new NotFoundException("404","Products not found with category "+category);
        }
        return listOfProducts;
    }


    // TODO: Method to filter products by price range
    public List<Product> filterByPriceRange(Double fromPrice,Double toPrice) throws NotFoundException{
        List<Product> listOfProducts = productRepository.findAll();
        List<Product> filteredProducts = listOfProducts.stream().filter(x -> x.getPrice() >= fromPrice && x.getPrice() <= toPrice).
        collect(Collectors.toList());
        if(filteredProducts.isEmpty()){
            throw new NotFoundException("404","No products in range of "+fromPrice+" and "+toPrice);
        }
        return filteredProducts;
    }


    // TODO: Method to filter products by stock quantity range
    public List<Product> filterByStockQuantityRange(Integer fromRange, Integer toRange) throws NotFoundException{
        List<Product> listOfProducts = productRepository.findAll();
        List<Product> filteredProducts = listOfProducts.stream().filter(x -> x.getStockQuantity() >= fromRange && 
                      x.getStockQuantity() <= toRange).collect(Collectors.toList());
                      if(filteredProducts.isEmpty()){
                        throw new NotFoundException("404","Products not found in range of "+fromRange+" and "+toRange);
                      }
                      return filteredProducts;
    }

}
