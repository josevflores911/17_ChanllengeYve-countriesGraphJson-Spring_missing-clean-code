package com.testJose.jsonReader.service;

import com.testJose.jsonReader.domain.Country;
import com.testJose.jsonReader.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchService {

    private CountryRepository countryRepository;

    public SearchService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public boolean countryNotFounded(String departure, String arrival){

        Boolean founded= true;

        String departureCountry= null;
        String arrivalCountry= null;

        for(Country country : countryRepository.findAll()) {
            if (country.getCca3().equals(departure)) {
                departureCountry=country.getRegion();
                if(country.getBorders().length==0){
                    founded=false;
                }
            }
            if (country.getCca3().equals(arrival) ) {
                arrivalCountry=country.getRegion();
                if(country.getBorders().length==0){
                    founded=false;
                }
            }

        }
        if(departureCountry.equals("Americas") && !arrivalCountry.equals("Americas")){
            founded = false;

        }else
        if(((departureCountry.equals("Europe"))
                || (departureCountry.equals("Asia"))
                || (departureCountry.equals("Africa"))) &&  (arrivalCountry.equals("Americas"))  ){
            founded = false;
        }
        return founded;
    }

    public List<String> pathCountry(String departure, String arrival) {

        List<Double> pathDistances=new ArrayList<>();
        List<String> pathCountries=new ArrayList<>();

        Double departureLatitud=null;
        Double departureLongitud=null;
        Double arrivalLatitud=null;
        Double arrivalLongitud=null;
        Double distance;
        Double savedDistance;

        for (Country country : countryRepository.findAll()) {
            if (country.getCca3().equals(departure)) {
                departureLatitud= Double.valueOf(Arrays.asList(country.getLatlng()).get(0));
                departureLongitud= Double.valueOf(Arrays.asList(country.getLatlng()).get(1));
            }
            if (country.getCca3().equals(arrival)) {
                arrivalLatitud= Double.valueOf(Arrays.asList(country.getLatlng()).get(0));
                arrivalLongitud= Double.valueOf(Arrays.asList(country.getLatlng()).get(1));
            }
        }

        savedDistance= Math.sqrt( Math.pow((departureLatitud-arrivalLatitud),2)
                +Math.pow((departureLongitud-arrivalLongitud),2) );
        pathDistances.add(savedDistance);
        pathCountries.add(departure);
        while (savedDistance != 0) {
            for (Country country : countryRepository.findAll()) {
                if(country.getCca3().equals(departure)){
                    for(int i= 0;i < Arrays.asList(country.getBorders()).size(); i++){
                        for(Country borderCountries : countryRepository.findAll()){
                            if(borderCountries.getCca3().equals(Arrays.asList(country.getBorders()).get(i))){
                                departureLatitud= Double.valueOf(Arrays.asList(borderCountries.getLatlng()).get(0));
                                departureLongitud= Double.valueOf(Arrays.asList(borderCountries.getLatlng()).get(1));
                                distance= Math.sqrt( Math.pow((departureLatitud-arrivalLatitud),2)
                                        +Math.pow((departureLongitud-arrivalLongitud),2) );
                                if(distance<=savedDistance ){
                                    savedDistance=distance;
                                    departure=borderCountries.getCca3();
                                }
                            }
                        }
                    }
                    pathCountries.add(departure);
                    pathDistances.add(savedDistance);
                }
            }

        }

        if(pathCountries.size()>2){
            pathCountries.remove(pathCountries.size()-1);
            pathDistances.remove(pathDistances.size()-1);
        }
        return pathCountries;
    }
}
