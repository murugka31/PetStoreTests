package com.emurugova.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDataResponse {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("petId")
    public Integer petId;

    @JsonProperty("quantity")
    public Integer quantity;

    @JsonProperty("shipDate")
    public String shipDate;

    @JsonProperty("status")
    public String status;

    @JsonProperty("complete")
    public String complete;
}
