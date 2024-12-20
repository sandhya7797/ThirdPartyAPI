package com.scaler.thirdpartyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThirdPartyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThirdPartyApiApplication.class, args);

		/*
		TODO List:
		1. FAKE STORE Patch API is not working.
		2. Finish User and Category API's from this page https://fakestoreapi.com/docs.
		3. Database versioning not testing or implemented because of JPA Buddy not working.
		4. Authorization testing pending. (Get All Products API using token)
		 */
	}

}
