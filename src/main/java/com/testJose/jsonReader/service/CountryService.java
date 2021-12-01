package com.testJose.jsonReader.service;

import com.testJose.jsonReader.domain.Country;
import com.testJose.jsonReader.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryService {

   @Autowired
    private SearchService searchMethods;

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public  Iterable<Country> list(){
        return countryRepository.findAll();
    }

    public Country save(Country country){
        return countryRepository.save(country);
    }

    public Iterable<Country> saveAll(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }

    public List<String> findAll(){
        ArrayList<String> allCountries = new ArrayList<String>();
        for(Country country : countryRepository.findAll()) {
            allCountries.add(Arrays.toString(country.getBorders()));
        }
        return allCountries;
    }

    public List<String> getCountryByBorders(String departure,String arrival){
        List<String> closestCountries=new ArrayList<>();

        if(searchMethods.countryNotFounded(departure, arrival)){
            closestCountries = searchMethods.pathCountry(departure, arrival);
        }
        return closestCountries;
    }
}
