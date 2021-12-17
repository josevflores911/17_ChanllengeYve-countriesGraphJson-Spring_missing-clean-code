package com.testJose.jsonReader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * subclass of country
 */
@Data
@AllArgsConstructor
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pap {

    @Column( name = "pap_official")
    private String official;
    @Column( name = "common_official")
    private String common;

    public Pap(){}
}
