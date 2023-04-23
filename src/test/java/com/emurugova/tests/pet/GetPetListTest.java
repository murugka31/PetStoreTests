package com.emurugova.tests.pet;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.tests.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.petResponse;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
@Feature("GET")
public class GetPetListTest extends TestBase {

    @Test
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void getAvailablePetsListTest () {
       String status = "available";
       given().filter(customLogFilter().withCustomTemplates())
              .when()
              .get("pet/findByStatus?status="+status)
              .then()
              .spec(petResponse);
    }

    @Test
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void getPendingPetsListTest () {
        String status = "pending";
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .when()
               .get("pet/findByStatus?status="+status)
               .then()
               .spec(petResponse);
    }

    @Test
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void getSoldPetsListTest () {
        String status = "sold";
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .when()
               .get("pet/findByStatus?status="+status)
               .then()
               .spec(petResponse);
    }
}