package com.emurugova.tests.pet;

import com.emurugova.models.Pet;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.petResponse;
import static com.emurugova.tests.TestData.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddPetTest extends TestBase {

    @Tag("petTest")
    @Test
    void addPetToTheListTest () {
        String newPetData = TestData.newPetData;
        Pet pet = given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType(JSON)
                .body(newPetData)
                .when()
                .post("pet/")
                .then()
                .spec(petResponse)
                .extract().as(Pet.class);

        assertEquals(petId, pet.getId());
        assertEquals(petName, pet.getName());
        assertEquals(petStatus, pet.getStatus());
        assertEquals(categoryName, pet.getCategory().getName());
    }
}

