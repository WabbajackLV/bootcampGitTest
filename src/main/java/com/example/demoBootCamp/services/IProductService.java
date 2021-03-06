package com.example.demoBootCamp.services;

import java.util.ArrayList;

import com.example.demoBootCamp.enums.ProductType;
import com.example.demoBootCamp.models.Product;

public interface IProductService {
	//select
	Product selectOneProductById(int id) throws Exception;
	ArrayList<Product> selectAllProducts();
	
	//create
	boolean insertNewProduct(String title, float price, ProductType type);
	boolean insertNewProductByObject(Product product);
	
	
	//update
	boolean updateProductById(int id, String title, float price);
	boolean updateProductObjectById(int id, Product product);
	
	//delete
	boolean deleteProductById(int id);
	
	//filtering
	ArrayList<Product> selectProductsWherePriceLessThan(float price);
	
	//save testing data
	void saveTestingData();
	
	
}
