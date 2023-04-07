package com.management;

import com.management.entities.Product;
import com.management.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class MainApplicationApplicationTests {
	@Autowired
	ProductRepository repository;

	@Test
	public void TestCreateProduct(){
		Product product = new Product("LG TV",4000.00,new Date());
		repository.save(product);
	}

	@Test
	public void TestFindProductById(){
		Product product = repository.findById(1L).get();
		System.out.println(product);
	}

	@Test
	public void TestFindAllProducts(){
		List<Product> products = repository.findAll();
		products.forEach(System.out::println);
	}

	@Test
	public void TestUpdateProduct(){
		Product product = repository.findById(1L).get();
		product.setPriceProduct(2500.00);
 		repository.save(product);
	}

	@Test
	public void TestDeleteProductById(){
		repository.deleteById(1L);
	}

	@Test
	public void TestDeleteAllProducts(){
		repository.deleteAll();
	}
}
