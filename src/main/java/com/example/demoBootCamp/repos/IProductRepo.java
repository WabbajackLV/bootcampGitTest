package com.example.demoBootCamp.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.demoBootCamp.enums.ProductType;
import com.example.demoBootCamp.models.Customer;
import com.example.demoBootCamp.models.Product;

public interface IProductRepo extends CrudRepository<Product, Integer>{

	boolean existsByTitleAndPriceAndType(String title, float price, ProductType type);

	ArrayList<Product> findByPriceLessThan(float price);
	
	ArrayList<Product> findByPrice(float price);

	ArrayList<Product> findByCustomer(Customer customer);
	
	Product findByTitleAndPrice(String title,float price);

}
