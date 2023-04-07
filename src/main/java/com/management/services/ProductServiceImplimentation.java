package com.management.services;

import com.management.entities.Product;
import com.management.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplimentation implements ProductService{
    @Autowired
    ProductRepository repository;
    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public void deleteProduct(Product product) {
        repository.delete(product);
    }

    @Override
    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        repository.deleteAll();
    }
}
