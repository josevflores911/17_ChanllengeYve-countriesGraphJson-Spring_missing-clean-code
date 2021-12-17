package com.testJose.jsonReader.service;

import com.testJose.jsonReader.domain.Country;
import com.testJose.jsonReader.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *  methods used in the project: save, search all elements,
 *  search all country names, get the list of countries that
 *  form a trayectoria between a country of origin and destination
 *
 */
@Service
public class CountryService {

    @Autowired
    private SearchService searchMethods;

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public Country save(Country country){
        return countryRepository.save(country);
    }


    /**
     * stores all json elements within the database
     *
     * @param countries
     * @return
     */
    public Iterable<Country> saveAll(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }

    public  Iterable<Country> list(){
        return countryRepository.findAll();
    }

    public List<String> findAll(){
        ArrayList<String> allCountries = new ArrayList<String>();
        for(Country country : countryRepository.findAll()) {
            allCountries.add(Arrays.toString(country.getBorders()));
        }
        return allCountries;
    }


    /**
     *method takes origin and destination as string and search the database returns a list of countries
     *
     * @param departure
     * @param arrival
     * @return closestCountries list of countries between origin and destination
     */
    public List<String> getCountryByBorders(String departure,String arrival){
        List<String> closestCountries=new ArrayList<>();

        if(searchMethods.countryNotFounded(departure, arrival)){
            closestCountries = searchMethods.pathCountry(departure, arrival);
        }
        return closestCountries;
    }
}
