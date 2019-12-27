package com.myretail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.myretail.model.Offer;
import com.myretail.repository.ProductRepository;



@SpringBootApplication
public class MyRetailApplication implements CommandLineRunner {
	
	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);
	}

	
	/**
	Inserting data on start-up of application
	**/
	@Override
	public void run(String... args) throws Exception {
		productRepository.save(new Offer(15117729, Double.valueOf(12), "USD"));
		productRepository.save(new Offer(16483589, Double.valueOf(67), "USD"));
		productRepository.save(new Offer(16696652, Double.valueOf(73), "USD"));
		productRepository.save(new Offer(16752456, Double.valueOf(18), "USD"));
		
		//redsky ID's
		productRepository.save(new Offer(13860428, Double.valueOf(54), "USD"));
		productRepository.save(new Offer(54439661, Double.valueOf(180.49), "USD"));
		//unittest
		productRepository.save(new Offer(53494594, Double.valueOf(20.49), "USD"));
		productRepository.save(new Offer(51552470, Double.valueOf(120.49), "USD"));
		productRepository.save(new Offer(78842191, Double.valueOf(150.49), "USD"));
		productRepository.save(new Offer(53184864, Double.valueOf(10.00), "USD"));
		
		
	}
	
	
}
