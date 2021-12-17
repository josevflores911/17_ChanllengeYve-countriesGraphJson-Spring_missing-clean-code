package com.testJose.jsonReader.repository;

import com.testJose.jsonReader.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * interface that links our repository with jpa methods
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    //List<Country> getCountryByBorders(String borders);
}
