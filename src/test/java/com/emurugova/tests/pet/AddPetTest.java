package com.emurugova.tests.pet;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.models.Pet;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.specs.Specs.*;
import static com.emurugova.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class AddPetTest extends TestBase {

    @Test
    @AllureId("17565")
    @DisplayName("Добавить животное в базу данных")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("petTest")})
    void addPetToTheListTest() {
        String newPetData = TestData.newPetData;
        step("Добавляем животное в БД", () -> {
            Pet pet = given()
                    .spec(request)
                    .body(newPetData)
                    .when()
                    .post("pet/")
                    .then()
                    .spec(successfulResponse)
                    .extract().as(Pet.class);

            assertEquals(petId, pet.getId());
            assertEquals(petName, pet.getName());
            assertEquals(petStatus, pet.getStatus());
            assertEquals(categoryName, pet.getCategory().getName());
        });
    }
}