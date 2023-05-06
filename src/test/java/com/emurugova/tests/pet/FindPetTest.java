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
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class FindPetTest extends TestBase {

    private TestData testData = new TestData();
    private PetDataRequest petDataRequest = new PetDataRequest();

    @Test
    @AllureId("17564")
    @DisplayName("Find pet by ID")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("petTest")})
    void findPetTest() {
        step("Добавляем животное в БД", () -> {
             given().spec(request)
                    .contentType(JSON)
                    .body(petDataRequest.petRequest().toString())
                    .when()
                    .post("pet/");
        });

        step("Находим животное в БД", () -> {
             given().spec(request)
                    .contentType(JSON)
                    .when()
                    .get("pet/" + petDataRequest.getTestData().petId)
                    .then()
                    .spec(successfulResponse())
                    .body("name", is(petDataRequest.getTestData().petName))
                    .body("id", is(petDataRequest.getTestData().petId));
        });
    }

    @Test
    @AllureId("17563")
    @DisplayName("Find a non-existent pet")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void findNoPetTest() {
        step("Находим несуществующее животное в БД", () -> {
             given().spec(request)
                    .contentType(JSON)
                    .when()
                    .get("pet/" + testData.noPetId)
                    .then()
                    .spec(response(404))
                    .body("code", is(1))
                    .body("type", is("error"))
                    .body("message", is("Pet not found"));
        });
    }
}