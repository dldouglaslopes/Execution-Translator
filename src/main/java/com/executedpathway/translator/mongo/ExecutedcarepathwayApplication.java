package com.executedpathway.translator.mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExecutedcarepathwayApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(ExecutedcarepathwayApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {	
		
		/*
		 * Medicine medicine = new Medicine();
		medicine.setBrand("BIOCHIMICO");
		
		Example<Medicine> example = Example.of(medicine);
		//repository.findOne(example);
		 */
	}
}
