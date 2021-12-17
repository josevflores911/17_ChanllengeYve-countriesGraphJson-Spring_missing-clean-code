package com.testJose.jsonReader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.testJose.jsonReader.domain.Country;
import com.testJose.jsonReader.service.CountryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class JsonReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonReaderApplication.class, args);
	}

	/**
	 *
	 * spring bean that maps and loads the data found in resources json
	 *
	 * @param countryService
	 * @return args
	 */
	@Bean
	CommandLineRunner runner(CountryService countryService){
		return args -> {
			//read json and write into db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>(){};
			InputStream inputStream =TypeReference.class.getResourceAsStream("/jsonData/countries.json");
			try{
				List<Country> countries = mapper.readValue(inputStream,typeReference);
				countryService.saveAll(countries);
				System.out.println("Users Saved!");

			}catch (IOException e){
				System.out.println(e.getMessage());
			}
		};
	}

}
