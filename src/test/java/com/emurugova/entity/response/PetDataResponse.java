package com.emurugova.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetDataResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("name")
    private String name;

    @JsonProperty("photoUrl")
    private List<String> photoUrls;

    @JsonProperty("tags")
    private List<Tags> tags;

    @JsonProperty("status")
    private String status;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Category {

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("name")
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Tags {

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("name")
        private String name;
    }
}