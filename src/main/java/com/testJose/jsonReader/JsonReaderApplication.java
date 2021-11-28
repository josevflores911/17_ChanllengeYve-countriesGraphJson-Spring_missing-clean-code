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

				System.out.println(countryService.findAll().get(0).getClass());



				/*3) third try
				List<String> selectedCountry = Arrays.asList(countries.get(2).getBorders());
				System.out.println(selectedCountry);
				System.out.println(selectedCountry.get(0));
				*/

				 /*
				/*2) second try
				countries.stream()
						.filter(e->e.getBorders().length == 0)
						.forEach(e->System.out.println(e.getName().getOfficial()+" have no borders"+Arrays.toString(e.getBorders()) ));

				System.out.println("----------------------------");
				countries.stream()
						.filter(e->e.getBorders().length > 0)
						.forEach(e->System.out.println(e.getName().getOfficial()+" have  borders "+Arrays.toString(e.getBorders()) ));
				*/



				/*1) first try
				equivalente -> for( int i = 0; i < countries.size(); i++ ) {

				for(Country country : countries) {
					for( int j = 0; j < country.getBorders().length; j++ ) {
						System.out.printf(" $ " + country.getBorders()[j]);
					}
					System.out.println(country.getBorders().length);
					System.out.println("stop");
				}
				System.out.println(countries.contains(""));

				System.out.println(countries.get(2).getBorders().getClass());
				*/
			}catch (IOException e){
				System.out.println(e.getMessage());
			}
		};
	}

}
