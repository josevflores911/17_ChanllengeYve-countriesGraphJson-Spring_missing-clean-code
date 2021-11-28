package com.testJose.jsonReader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
