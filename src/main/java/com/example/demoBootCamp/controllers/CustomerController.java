package com.example.demoBootCamp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demoBootCamp.models.Customer;
import com.example.demoBootCamp.models.Product;
import com.example.demoBootCamp.services.ICustomerService;
import com.example.demoBootCamp.services.IProductService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService custService;
	
	@Autowired
	IProductService prodService;

	@GetMapping("/showMyProduct/{id}")
	String getShowMyProductsByCustId(@PathVariable (name = "id") int id, Model model) {
		try {
			model.addAttribute("innerObjectProd", custService.getAllPurchasedProductsByCustId(id));
			model.addAttribute("innerObjectCust", custService.getOneCustomer(id).getName());
			return "show-all-customer-products-page";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error";
		}
		
	}
	
	@GetMapping("/showAllCustomers")
	String getShowAllCustomers(Model model) {
		model.addAttribute("innerObject", custService.selectAllCustomers());
		return "show-all-customers-page";
	}
	
	@GetMapping ("/registerCustomer")
	String getRegisterCustomer(Customer customer) {
		return "register-customer-page";
	}
	
	@PostMapping("/registerCustomer")
	String postRegisterCustomer(@Valid Customer customer, BindingResult result) {
		System.out.println(customer);
		if(result.hasErrors()) {
			return "register-customer-page";
		}
		
		try {
			custService.register(customer.getName(), customer.getAge());
			return "redirect:/customer/showAllCustomers";
		} catch (Exception e) {
			return "error";
		}
		
	}
	
	@GetMapping("/buy/{id}")
	String getBuyProductsByCustId(@PathVariable (name = "id")int id, Model model, Customer customer) {
		try {
			model.addAttribute("innerObjectCustName", custService.getOneCustomer(id).getName());
			model.addAttribute("allCustomerProducts", prodService.selectAllProducts());
			return "customer-buy";
		} catch (Exception e) {
			return "error";
		}	
	}
	
	@PostMapping("/buy/{id}")
	String postBuyProductsByCustId(@PathVariable (name = "id")int id, Customer customer) {
		for(Product prod : customer.getAllCustomerProducts()) {
			System.out.println(prod.getTitle() + " " + prod.getPrice());
		}
		
		try {
			custService.buyProducts(customer.getAllCustomerProducts(), id);
			return "reidrect:/customer/showMyProducts/" + id;
			
		} catch (Exception e) {
			return "error";
		}	
	}
	
	//buy products
	
	
	
	
	
}
