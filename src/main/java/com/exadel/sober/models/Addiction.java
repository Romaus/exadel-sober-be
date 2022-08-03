package com.exadel.sober.models;


import lombok.Data;
import java.util.List;

@Data
public class Addiction {

    private Integer addictionId;

    private String name;

    private String description;

    List<Promise> promises;

}