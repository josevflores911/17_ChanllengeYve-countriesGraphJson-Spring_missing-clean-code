package com.testJose.jsonReader.service;

import com.testJose.jsonReader.domain.Country;
import com.testJose.jsonReader.domain.Name;
import com.testJose.jsonReader.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryService {


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
       List<String> departureCountry= new ArrayList<String>();
       List<String> arrivalCountry= new ArrayList<String>();
       List<String> closestCountries= new ArrayList<String>();



       for(Country country : countryRepository.findAll()) {
            if (country.getCca3().equals(departure)) {
                departureCountry=Arrays.asList(country.getBorders());
                //System.out.println(selectedCountry);
            }
           if (country.getCca3().equals(arrival)) {
               arrivalCountry=Arrays.asList(country.getBorders());
               //System.out.println(selectedCountry);
           }
        }
        if (departureCountry.size()>0 && arrivalCountry.size()>0) {
            for (Country country : countryRepository.findAll()) {
                for (int i = 0; i < Arrays.asList(country.getBorders()).size(); i++) {

                    //System.out.println(  (Arrays.asList(country.getBorders()).get(i)).equals(departure)  );

                    if(( (Arrays.asList(country.getBorders()).get(i)).equals(departure)  ) || (  (Arrays.asList(country.getBorders()).get(i)).equals(arrival) ) ){
                        //closestCountries.add(Arrays.toString(country.getBorders()));
                        closestCountries.add(country.getName().getCommon());
                        //closestCountries.add(country.getCca3());

                    }
                    //System.out.println(selectedCountry.get(i));
                }
            }
            return closestCountries;
        }else{
             //404
            return closestCountries;
        }
    }
}
