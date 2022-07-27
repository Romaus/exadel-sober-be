package com.exadel.sober.models;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Reason {

    @Id
    private Integer reasonId;

    private Integer promiseId;

    private String description;

}