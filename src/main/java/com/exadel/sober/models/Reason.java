package com.exadel.sober.models;
import lombok.Data;

@Data
public class Reason {

    private Integer reasonId;

    private Integer promiseId;

    private String description;

}