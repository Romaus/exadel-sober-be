package com.exadel.sober.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Promise {

    @Id
    private Integer promiseId;

    private Integer userId;

    private Integer addictionId;

    private String name;

    private Timestamp expiredDate;

    @MappedCollection(keyColumn = "promise_id",idColumn = "promise_id")
    List<Reason> reasons;

}