package com.emurugova.tests.pet;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.PetDataRequest;
import com.emurugova.entity.response.PetDataResponse;
import com.emurugova.tests.TestBase;
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

    private PetDataRequest petDataRequest = new PetDataRequest();

    @Test
    @AllureId("17565")
    @DisplayName("Add a new pet to the store")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("petTest")})
    void addPetToTheListTest() {
        step("Добавляем животное в БД", () -> {
            PetDataResponse pet = given()
                    .spec(request)
                    .body(petDataRequest.petRequest().toString())
                    .when()
                    .post("pet/")
                    .then()
                    .spec(successfulResponse())
                    .extract().as(PetDataResponse.class);

            assertEquals(petId, pet.getId());
            assertEquals(categoryId, pet.getCategory().getId());
            assertEquals(categoryName, pet.getCategory().getName());
            assertEquals(petName, pet.getName());
            assertEquals(petTagsId, pet.getTags().get(0).getId());
            assertEquals(petTagsName, pet.getTags().get(0).getName());
            assertEquals(petStatus, pet.getStatus());
        });
    }
}