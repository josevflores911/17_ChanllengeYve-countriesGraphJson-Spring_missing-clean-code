package com.testJose.jsonReader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * subclass of country
 */
@Data
@AllArgsConstructor
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currencies {

    @Embedded
    private Awg awg;

    public Currencies(){}

}
