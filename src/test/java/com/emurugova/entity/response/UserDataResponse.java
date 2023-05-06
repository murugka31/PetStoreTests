package com.emurugova.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDataResponse {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("username")
    public String username;

    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("email")
    public String email;

    @JsonProperty("password")
    public String password;

    @JsonProperty("phone")
    public String phone;

    @JsonProperty("userStatus")
    public Integer userStatus;
}
