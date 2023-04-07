package com.management.services;

import com.management.entities.Product;

import java.util.List;

public interface ProductService {
     Product saveProduct(Product product);
     Product updateProduct(Product product);
     Product getProduct(Long id);
     List<Product> getAllProducts();
     void deleteProduct(Product product);
     void deleteProductById(Long id);
     void deleteAllProducts();
}
