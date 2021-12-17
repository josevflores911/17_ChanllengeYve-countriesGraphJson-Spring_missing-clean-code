package com.testJose.jsonReader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Class that generates the model that will be sent in the data table
 *
 *   application of Lombok annotations for the construction of getters and setters and
 *   constructors with all arguments and no arguments as well as
 *  builder for instantiating new objects
 *
 *  this class takes the elements from the json file and transforms them
 *  into objects, for efficiency, not all json elements were included
 *  with the jsonIgnoreProperties annotation
 *
 *  the main class has subclasses that are located at the end of
 *  the code lines of this class which in turn have variables and subclasses *
 *
 *
 * @author Jose Viera
 * @version 1.1
 *
 */
@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String[] tld;
    private String cca2;
    private String ccn3;
    private String cca3;
    private String cioc;
    private Boolean independent;
    private String status;
    private Boolean unMember;
    private String[] capital;
    //private String[] altSpellings;
    private String region;
    private String subregion;
    private Integer[] latlng;
    private Boolean landlocked;
    private String[] borders;
    private Long area;
    private String flag;//character

    @Embedded
    private Name name;
    @Embedded
    private Currencies currencies;
    @Embedded
    private Idd idd;
    @Embedded
    private Language language;
    @Embedded
    private Demonyms demonyms;

    public Country(){   }

}
