package com.example.demoBootCamp.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demoBootCamp.models.Product;
import com.example.demoBootCamp.services.IProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	IProductService prodService;

	@GetMapping("/showAllProducts")
	public String getShowAllProducts(Model model) {
		model.addAttribute("innerObject", prodService.selectAllProducts());
		return "show-all-products-page";
	}
	
	@GetMapping("/saveTestingData")
	public String getSaveTestingData() {
		prodService.saveTestingData();
		return "hello-page";
	}
	
	@GetMapping("/showAllProducts/{id}")
	public String getShowAllProductId(@PathVariable(name="id") int id, Model model) {
		try {
			model.addAttribute("innerObject", prodService.selectOneProductById(id));
			return "show-one-product-page";
		}catch(Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/insertOneProduct")
    public String getInsertOneProduct(Product product)
    {
		return "insert-one-product-page";
    }
    
    @PostMapping("/insertOneProduct")
    public String postInsertOneProduct(@Valid Product product, BindingResult result)
    {
    	System.out.println(product);
    	if(result.hasErrors()) {
    		return "insert-one-product-page";
    	}
    	
    	
    	prodService.insertNewProduct(product.getTitle(), product.getPrice(), product.getType());
    	return "redirect:/product/showAllProducts";
            
    }
    
    @GetMapping("/updateProduct/{id}")
    public String getUpdateProductById(@PathVariable(name = "id") int id, Model model, Product product) {
    	try {
			Product productForUpdate = prodService.selectOneProductById(id);
			model.addAttribute("innerObject", productForUpdate);
			return "update-one-product-page";
			} catch (Exception e) {
			return "error";
		}
    }
    
    @PostMapping("/updateProduct/{id}")
    public String postUpdateProductById(@PathVariable(name = "id") int id, Model model, Product product) {
    	System.out.println(product);
    	
    	prodService.updateProductObjectById(id, product);
    	return "redirect:/product/showAllProducts";
    	
    }
    
    @GetMapping("/deleteProduct/{id}")
    public String getDeleteProductById(@PathVariable(name = "id") int id, Model model, Product product) {
    	try {
    		prodService.deleteProductById(id);
    		
			model.addAttribute("innerObject", prodService.selectAllProducts());
			return "show-all-products-page";
			} catch (Exception e) {
			return "error";
		}
    }
    
    @GetMapping("/gitTest")
    public String getGitTest() {
    	return "hello-page";
    }
    
  
	
}
