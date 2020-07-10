package com.example.demoBootCamp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demoBootCamp.enums.ProductType;



@Entity
@Table(name = "ProductTable")
public class Product {
	
	@Column(name = "Title")
	@Size(min = 3, max = 20)
	@Pattern(regexp = "[a-zA-Z\\s]+$")
	private String title;
	
	@Column(name = "Price")
	@Min(0)
	@Max(1000)
	private float price;

	@Column(name = "ProdType")
	private ProductType type = ProductType.Fruits;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "CiD")
	private Customer customer;
	
	
	
	
	
	
	//private static int idCounter = 1;
	
	public Product(@Size(min = 3, max = 20) @Pattern(regexp = "[a-zA-Z\\s]+$") String title,
			@Min(0) @Max(1000) float price, ProductType type, Customer customer) {
		super();
		this.title = title;
		this.price = price;
		this.type = type;
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product() {
		
	}
	
	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public Product(String title, float price, ProductType type) {
		super();
		this.title = title;
		this.price = price;
		this.type = type;
	
	}

	

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return title + " " + price + " eur";
	}
	
	
	
}
