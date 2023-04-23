package com.emurugova.tests.pet;

import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class DeletePetTest extends TestBase {

    @Tag("petTest")
    @Test
     void deletePetTest () {
        Integer petId = TestData.petId;
        String newPetData = TestData.newPetData;
        step("Create a pet", () -> {
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .body(newPetData)
               .when()
               .post("pet/");
                });

        step("Delete a pet", () -> {
        given().filter(customLogFilter().withCustomTemplates()).contentType(JSON)
               .when()
               .delete("pet/"+petId)
               .then()
               .spec(deletePetResponse);
        });
    }

    @Tag("petTest")
    @Test
    void deleteNoExistedPetTest () {
        Integer noPetId = TestData.noPetId;
        given().filter(customLogFilter().withCustomTemplates()).contentType(JSON)
               .contentType(JSON)
               .when()
               .delete("pet/"+noPetId)
               .then()
               .statusCode(404);
    }
}
