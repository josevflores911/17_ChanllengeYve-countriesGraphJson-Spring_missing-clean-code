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

    private CountryService countryService;

    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/list")
    public Iterable<Country> list(){
        return countryService.list();
    }

    @GetMapping("/borderList")
    public List<String> findAll(){
        return countryService.findAll();
    }

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
