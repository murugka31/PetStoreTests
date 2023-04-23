package com.emurugova.tests.pet;

import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.noExistedPetResponse;
import static com.emurugova.specs.Specs.petResponse;
import static com.emurugova.tests.TestData.petName;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class FindPetTest extends TestBase {

    @Tag("petTest")
    @Test
    void findPetTest () {
        int petId = TestData.petId;
        String newPetData = TestData.newPetData;
        step("Create a pet", () -> {
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .body(newPetData)
               .when()
               .post("pet/");
        });

        step("Find a pet", () -> {
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .when()
               .get("pet/"+petId)
               .then()
               .spec(petResponse)
               .body("name", is(petName))
               .body("id", is(petId));
        });
    }

    @Tag("petTest")
    @Test
    void findNoPetTest () {
        int noPetId = TestData.noPetId;
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .when()
               .get("pet/"+noPetId)
               .then()
               .spec(noExistedPetResponse);
    }
}
