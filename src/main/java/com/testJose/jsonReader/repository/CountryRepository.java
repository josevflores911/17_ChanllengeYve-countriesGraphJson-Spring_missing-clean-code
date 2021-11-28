package com.testJose.jsonReader.repository;

import com.testJose.jsonReader.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> getCountryByBorders(String borders);

}
