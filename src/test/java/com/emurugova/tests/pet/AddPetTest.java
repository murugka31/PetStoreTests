package com.emurugova.tests.pet;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.PetDataRequest;
import com.emurugova.entity.response.PetDataResponse;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class AddPetTest extends TestBase {

    private TestData testData = new TestData();
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

            assertEquals(petDataRequest.getTestData().petId, pet.getId());
            assertEquals(petDataRequest.getTestData().categoryId, pet.getCategory().getId());
            assertEquals(petDataRequest.getTestData().categoryName, pet.getCategory().getName());
            assertEquals(petDataRequest.getTestData().petName, pet.getName());
            assertEquals(petDataRequest.getTestData().petTagsId, pet.getTags().get(0).getId());
            assertEquals(petDataRequest.getTestData().petTagsName, pet.getTags().get(0).getName());
            assertEquals(petDataRequest.getTestData().petStatus, pet.getStatus());
        });
    }
}