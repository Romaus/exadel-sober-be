package com.exadel.sober.models;

import lombok.Data;

import java.util.List;
@Data
public class User {

    private Integer userId;

    private String name;

    private String email;

    private String password;

    List<Promise> promises;

}