package com.example.demoBootCamp.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demoBootCamp.enums.ProductType;
import com.example.demoBootCamp.models.Customer;
import com.example.demoBootCamp.models.Product;
import com.example.demoBootCamp.repos.ICustomerRepo;
import com.example.demoBootCamp.repos.IProductRepo;
import com.example.demoBootCamp.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	IProductRepo prodRepo;
	
	@Autowired
	ICustomerRepo custRepo;
	

	@Override
	public Product selectOneProductById(int id) throws Exception {
		if(id > 0 ) {
			/*
			for(int i = 0; i < allProducts.size(); i++) {
				if(allProducts.get(i).getId() == id) {
					return allProducts.get(i);
				}
			}
			*/
			if(prodRepo.existsById(id)) {
				return prodRepo.findById(id).get();
			}
		}
		throw new Exception("Did not find the product");
	}

	@Override
	public ArrayList<Product> selectAllProducts() {
		return (ArrayList<Product>) prodRepo.findAll();
	}

	@Override
	public boolean insertNewProduct(String title, float price, ProductType type) {
		/*
		for(int i = 0; i < allProducts.size(); i++) {
			if(allProducts.get(i).getTitle().equals(title) && allProducts.get(i).getPrice() == price) {
				return false;
			}
		}
		allProducts.add(new Product(title, price));
		return true;
		*/
		if(prodRepo.existsByTitleAndPriceAndType(title, price, type)) {
			return false;
		}
		
		Product prod = new Product(title, price, type);
		prodRepo.save(prod);
		return true;
	}

	@Override
	public boolean insertNewProductByObject(Product product) {
		/*
		for(int i = 0; i < allProducts.size(); i++) {
			if(allProducts.get(i).getPrice() == product.getPrice() && allProducts.get(i).getTitle().equals(product.getTitle())) {
				return false;
			}
		}
		allProducts.add(product);
		return true;
		*/
		if(prodRepo.existsByTitleAndPriceAndType(product.getTitle(), product.getPrice(), product.getType())) {
			return false;
		}
		
		Product prod = new Product(product.getTitle(), product.getPrice(), product.getType());
		prodRepo.save(prod);
		return true;
	}

	@Override
	public boolean updateProductById(int id, String title, float price) {
		if(id > 0) {
			if(prodRepo.existsById(id)) {
				Product productToUpdate = prodRepo.findById(id).get();
				productToUpdate.setTitle(title);
				productToUpdate.setPrice(price);
				prodRepo.save(productToUpdate);
				return true;
			}
			/*
			for(int i = 0; i < allProducts.size(); i++) {
				if(allProducts.get(i).getId() == id) {
					allProducts.get(i).setTitle(title);
					allProducts.get(i).setPrice(price);
					return true;
				}
			}
			*/
			
		}
		return false;
	}

	@Override
	public boolean updateProductObjectById(int id, Product product) {
		if(id > 0) {
			if(prodRepo.existsById(id)) {
				Product productToUpdate = prodRepo.findById(id).get();
				productToUpdate.setTitle(product.getTitle());
				productToUpdate.setPrice(product.getPrice());
				prodRepo.save(productToUpdate);
				return true;
			}
			/*
			for(int i = 0; i < allProducts.size(); i++) {
				if(allProducts.get(i).getId() == id) {
					allProducts.get(i).setTitle(product.getTitle());
					allProducts.get(i).setPrice(product.getPrice());
					return true;
				}
			}
			*/
		}
		return false;
	}

	@Override
	public boolean deleteProductById(int id) {
		if(prodRepo.existsById(id)) {
			prodRepo.deleteById(id);
			return true;
		}
		/*
		for(int i = 0; i < allProducts.size(); i++) {
			if(allProducts.get(i).getId() == id) {
				allProducts.remove(i);
				return true;
			}
		}
		*/
		return false;
	}

	@Override
	public ArrayList<Product> selectProductsWherePriceLessThan(float price) {
		ArrayList<Product> selectedProducts = new ArrayList<Product>();
		selectedProducts = prodRepo.findByPriceLessThan(price);
		/*
		for(int i = 0; i < allProducts.size(); i++) {
			if(allProducts.get(i).getPrice() < price) {
				selectedProducts.add(allProducts.get(i));
			}
		}
		*/
		if(selectedProducts != null) {
			return selectedProducts;
		}
		
		return new ArrayList<Product>();
	}

	@Override
	public void saveTestingData() {
		Customer cust1 = new Customer("Janis", 18);
		Customer cust2 = new Customer("Baiba", 32);
		
		custRepo.save(cust1);
		custRepo.save(cust2);
		
		
		prodRepo.save(new Product("apple", 0.45f, ProductType.Fruits, cust1));
		prodRepo.save(new Product("bannana", 0.60f, ProductType.Fruits));
		prodRepo.save(new Product("potatoes", 0.90f, ProductType.Vegetables, cust2));
	}
	

}
