package com.example.demoBootCamp.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoBootCamp.models.Customer;
import com.example.demoBootCamp.models.Product;
import com.example.demoBootCamp.repos.ICustomerRepo;
import com.example.demoBootCamp.repos.IProductRepo;
import com.example.demoBootCamp.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	ICustomerRepo custRepo;
	
	@Autowired
	IProductRepo prodRepo;

	@Override
	public boolean register(String name, int age) throws Exception {
		if(custRepo.existsByNameAndAge(name, age)) {
			throw new Exception("Such a customer already exists");
		}
		custRepo.save(new Customer(name, age));
		return true;
	}

	@Override
	public ArrayList<Product> getAllPurchasedProductsByCustId(int id) throws Exception {
		if(id > 0) {
			if(custRepo.existsById(id)) {
				Customer cust = custRepo.findById(id).get();
				ArrayList<Product> purchasedProd = prodRepo.findByCustomer(cust);
				return purchasedProd;
			}
		}
		throw new Exception("Id is not correct");
	}

	@Override
	public boolean buyProducts(Collection<Product> purchasedProducts, int id) throws Exception {
		if(id > 0) {
			if(custRepo.existsById(id)) {
				Customer cust = custRepo.findById(id).get();
				
				for(Product p: purchasedProducts) {
				//for(int i = 0; i < purchasedProducts.size(); i++){
					//String titleTemp = purchasedProducts.get(i).getTitle();
					//float priceTemp = purchasedProducts.get(i).getPrice();
					//ArrayList<Product> prodTemp = (ArrayList<Product>) purchasedProducts;
					
					Product prod = prodRepo.findByTitleAndPrice(p.getTitle(), p.getPrice());
					prod.setCustomer(cust);
					prodRepo.save(prod);
					
				}
				return true;
			}
		}
		throw new Exception("Incorrect id");
	}

	@Override
	public Customer getOneCustomer(int id) throws Exception {
		if(id > 0) {
			if(custRepo.existsById(id)) {
				return custRepo.findById(id).get();
			}
		}
		throw new Exception("Customer not found");
	}

	@Override
	public ArrayList<Customer> selectAllCustomers() {
		if(custRepo.findAll() != null) {
			ArrayList<Customer> allCustomers = (ArrayList<Customer>) custRepo.findAll();
			return allCustomers;
		}
		return new ArrayList<Customer>();
	}


	
	
}
