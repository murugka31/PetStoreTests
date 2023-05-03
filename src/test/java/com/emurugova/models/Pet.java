package com.emurugova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {

    public Integer id;
    public Category category;
    public String name;
    public String photoUrls;
    public Tags tags;
    public String status;
}
