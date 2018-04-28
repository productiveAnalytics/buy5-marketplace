package com.buy5.marketplace;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.buy5.marketplace.model.Product;
import com.buy5.marketplace.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import static com.buy5.marketplace.model.Buy5Entity.OBJECT_MAPPER;

@SpringBootApplication
public class MarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ProductRepository prodRepo) {
		String[] jsons = {
							"{\"pid\": 2, \"name\": \"GalaxyNote9\", \"price\": 900, \"display_name\": \"Samsung Galaxy Note 9\", \"inventory\": 3, \"cat_id\": 100}",
							"{\"pid\": 1, \"name\": \"iPhoneX\", \"price\": 999, \"display_name\": \"Apple iPhone X\", \"inventory\": 10, \"cat_id\": 100}",
							"{\"pid\": 3, \"name\": \"LevisJeans\", \"display_name\": \"Levi's Jeans\", \"price\": 33, \"inventory\": 35, \"cat_id\": 400}",
							"{\"pid\": 4, \"name\": \"SurfacePro\", \"price\": 1500, \"display_name\": \"Microsoft Surface Pro\", \"inventory\": 0}"
						 };
		
		return args -> {
			Arrays.asList(jsons).stream()
								.forEach(jsonStr -> {
											try {
												Product p = OBJECT_MAPPER.readValue(jsonStr, Product.class);
												prodRepo.create(p);
											} catch (JsonParseException e) {
												e.printStackTrace();
											} catch (JsonMappingException e) {
												e.printStackTrace();
											} catch (IOException e) {
												e.printStackTrace();
											}
										 }
										);
			prodRepo.findAll().forEach(p -> System.out.println(p.getName()));
		};
	}
	
}
