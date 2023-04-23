package com.emurugova.tests.user;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.createNewUserResponse;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class CreateUser extends TestBase {

    @Test
    @Tags({@Tag("api"), @Tag("critical"), @Tag("userTest")})
    void createNewUserTest () {
        String newUserData = TestData.newUserData;
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .body(newUserData)
               .when()
               .post("user/")
               .then()
               .spec(createNewUserResponse);
    }
}
