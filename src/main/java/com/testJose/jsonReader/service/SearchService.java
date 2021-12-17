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


    /**
     * a method that searches for the possibility of paths between
     * the origin and the destination, taking into account if the
     * country has a border, or if they are on the same continent.
     * returns boolean true if there is any path and false if there
     * is no path option between countries. the purpose is to avoid
     * making a calculation between unconnected countries
     *
     * @param departure
     * @param arrival
     * @return founded
     */
    public boolean countryNotFounded(String departure, String arrival){

        Boolean founded= true;

        String departureCountry= null;
        String arrivalCountry= null;

        /**
         * compares the origin and destination in the database from
         * the initials of the country determined by cca3 and calculates
         * whether the longitud of the array of borders and mayor q cero if cero has no border         *
         */

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

        /**
         * determines if the countries are on the same continent
         */
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


    /**
     * method that determines the countries that form a
     * tray from the origin and the destination using latitude
     * and longitude as points of the coordinate axis. returns a list
     * of all countries that make up the trayectoria between origin and destination
     *
     * @param departure
     * @param arrival
     *
     * @return pathCountries
     */
    public List<String> pathCountry(String departure, String arrival) {

        List<Double> pathDistances=new ArrayList<>();
        List<String> pathCountries=new ArrayList<>();

        Double departureLatitud=null;
        Double departureLongitud=null;
        Double arrivalLatitud=null;
        Double arrivalLongitud=null;
        Double distance;
        Double savedDistance;

        /**
         * from cca3 (formally country initials) it determines
         * the latitude and longitude of the country of origin and destination
         */
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

        //formula to calculate the distance between countries from latitude and longitude
        savedDistance= Math.sqrt( Math.pow((departureLatitud-arrivalLatitud),2)
                +Math.pow((departureLongitud-arrivalLongitud),2) );
        pathDistances.add(savedDistance);
        pathCountries.add(departure);

        //loop that remains until it finds the last country of the route, a condition that is fulfilled when the distance between the countries from the formula is cero
        while (savedDistance != 0) {
            //loop to find the country's departure date in the database
            for (Country country : countryRepository.findAll()) {
                if(country.getCca3().equals(departure)){
                    //loop that determines the front of the origin
                    for(int i= 0;i < Arrays.asList(country.getBorders()).size(); i++){
                        //loop that determines the data of the countries bordering the origin
                        for(Country borderCountries : countryRepository.findAll()){
                            //when it finds one of the border countries it calculates the distance to determine the smallest
                            if(borderCountries.getCca3().equals(Arrays.asList(country.getBorders()).get(i))){
                                departureLatitud= Double.valueOf(Arrays.asList(borderCountries.getLatlng()).get(0));
                                departureLongitud= Double.valueOf(Arrays.asList(borderCountries.getLatlng()).get(1));
                                distance= Math.sqrt( Math.pow((departureLatitud-arrivalLatitud),2)
                                        +Math.pow((departureLongitud-arrivalLongitud),2) );
                                //keep the country with the smallest distance result that will become the new point of origin
                                if(distance<=savedDistance ){
                                    savedDistance=distance;
                                    departure=borderCountries.getCca3();
                                }
                            }
                        }
                    }
                    //when a country is founded add to the list until the distance become zero
                    pathCountries.add(departure);
                    pathDistances.add(savedDistance);
                }
            }
        }

        //remove the destination countrie if already exist in the list
        if(pathCountries.size()>2){
            pathCountries.remove(pathCountries.size()-1);
            pathDistances.remove(pathDistances.size()-1);
        }
        return pathCountries;
    }
}
