package com.example.demoBootCamp.services;

import java.util.ArrayList;
import java.util.Collection;

import com.example.demoBootCamp.models.Customer;
import com.example.demoBootCamp.models.Product;

public interface ICustomerService{
	
	boolean register(String name, int age) throws Exception;
	ArrayList<Product> getAllPurchasedProductsByCustId(int id) throws Exception;
	boolean buyProducts(Collection<Product> purchasedProducts, int id) throws Exception;
	Customer getOneCustomer(int id) throws Exception;
	ArrayList<Customer> selectAllCustomers();
	
	
	
	
}
