package com.testJose.jsonReader.controller;

import com.testJose.jsonReader.domain.Country;
import com.testJose.jsonReader.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/")
public class CountriesController {

    /**
     * Injection of service dependencies
     */
    private CountryService countryService;

    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * rest controller get method that calls
     * all values in the data table and returns a list of all the data in the json file serialized
     *
     * @return Iterable<Country>
     */
    @GetMapping("/list")
    public Iterable<Country> list(){
        return countryService.list();
    }

    /**
     * rest controller get method that calls
     * all countries in table and returns a list of all the countries in the json file serialized
     *
     * @return List<String>
     */
    @GetMapping("/borderList")
    public List<String> findAll(){
        return countryService.findAll();
    }

    /**
     *
     * rest controller method responsible for indicating the countries
     * that limit the trip and calling the class where the alternative
     * route between origin and destination is determined
     *
     * returns the list of countries if it finds it is a 200 ok state,
     * and a not found message and a 404 state if there is no travel alternative
     *
     * @param origin
     * @param destination
     * @return getCountryByBorders
     */
    @GetMapping("/routing/{origin}/{destination}")
    public ResponseEntity<List<String>> getCountryByBorders(@PathVariable("origin") String origin,@PathVariable("destination") String destination) {

        String departure = origin.toUpperCase(Locale.ROOT);
        String arrival = destination.toUpperCase(Locale.ROOT);
        List<String> travelsOptions = countryService.getCountryByBorders(departure,arrival);

        List<String> msg = new ArrayList<String>();
        msg.add("not founded path");

        if(travelsOptions.size()==0){
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(travelsOptions, HttpStatus.OK);
        }
    }
}
