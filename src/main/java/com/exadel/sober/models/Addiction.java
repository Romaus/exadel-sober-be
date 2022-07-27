package com.exadel.sober.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import java.util.List;

@Data
public class Addiction {

    @Id
    private Integer addictionId;

    private String name;

    private String description;

    @MappedCollection(keyColumn = "addiction_id", idColumn = "addiction_id")
    List<Promise> promises;

}