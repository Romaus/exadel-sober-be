package com.exadel.sober.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.List;
@Data
public class User {

    @Id
    private Integer userId;

    private String name;

    private String email;

    private String password;

    @MappedCollection(keyColumn = "user_id",idColumn = "user_id")
    List<Promise> promises;

}