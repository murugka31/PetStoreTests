package com.emurugova.tests.pet;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.PetDataRequest;
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
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class DeletePetTest extends TestBase {

    private TestData testData = new TestData();
    private PetDataRequest petDataRequest = new PetDataRequest();

    @Test
    @AllureId("17566")
    @DisplayName("Delete a pet")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("petTest")})
    void deletePetTest() {
        step("Добавляем животное в БД", () -> {
             given().spec(request)
                    .contentType(JSON)
                    .body(petDataRequest.petRequest().toString())
                    .when()
                    .post("pet/");
        });

        step("Удаляем животное из БД", () -> {
             given().spec(request)
                    .when()
                    .delete("pet/" + petDataRequest.getTestData().petId)
                    .then()
                    .spec(successfulResponse());
        });
    }

    @Test
    @AllureId("17567 ")
    @DisplayName("Delete a non-existent pet")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void deleteNoExistedPetTest() {
        step("Удаляем несуществующее животное из БД", () -> {
             given().spec(request)
                    .when()
                    .delete("pet/" + testData.noPetId)
                    .then()
                    .statusCode(404);
        });
    }
}