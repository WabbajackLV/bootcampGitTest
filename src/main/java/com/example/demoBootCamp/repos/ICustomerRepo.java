package com.example.demoBootCamp.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demoBootCamp.models.Customer;

public interface ICustomerRepo extends CrudRepository<Customer, Integer> {

	boolean existsByNameAndAge(String name, int age);

}
