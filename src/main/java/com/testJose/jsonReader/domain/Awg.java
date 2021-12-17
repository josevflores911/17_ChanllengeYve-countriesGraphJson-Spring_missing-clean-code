package com.testJose.jsonReader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * subclass of country
 */
@Data
@AllArgsConstructor
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Awg {

    private String name;
    private String symbol;

    public Awg(){}
}
