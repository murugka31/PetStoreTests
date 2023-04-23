package com.emurugova.tests.pet;

import com.emurugova.tests.TestBase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.petResponse;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class GetPetListTest extends TestBase {

    @Tag("petTest")
    @Test
    void getAvailablePetsListTest () {
       String status = "available";
       given().filter(customLogFilter().withCustomTemplates())
              .when()
              .get("pet/findByStatus?status="+status)
              .then()
              .spec(petResponse);
    }

    @Tag("petTest")
    @Test
    void getPendingPetsListTest () {
        String status = "pending";
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .when()
               .get("pet/findByStatus?status="+status)
               .then()
               .spec(petResponse);
    }

    @Tag("petTest")
    @Test
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
