package com.exadel.sober.models;


import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Promise {

    private Integer promiseId;

    private Integer userId;

    private Integer addictionId;

    private String name;

    private Timestamp expiredDate;

    private List<Reason> reasons;

}