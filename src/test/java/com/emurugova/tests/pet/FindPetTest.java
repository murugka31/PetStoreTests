package com.emurugova.tests.pet;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.specs.Specs.*;
import static com.emurugova.tests.TestData.petName;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class FindPetTest extends TestBase {

    @Test
    @AllureId("17564")
    @DisplayName("Найти животное в базе данных по ID")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("petTest")})
    void findPetTest() {
        int petId = TestData.petId;
        String newPetData = TestData.newPetData;
        step("Добавляем животное в БД", () -> {
             given().spec(request)
                    .contentType(JSON)
                    .body(newPetData)
                    .when()
                    .post("pet/");
        });

        step("Находим животное в БД", () -> {
             given().spec(request)
                    .contentType(JSON)
                    .when()
                    .get("pet/" + petId)
                    .then()
                    .spec(successfulResponse)
                    .body("name", is(petName))
                    .body("id", is(petId));
        });
    }

    @Test
    @AllureId("17563")
    @DisplayName("Найти несуществующее животное в базе данных")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void findNoPetTest() {
        int noPetId = TestData.noPetId;
        step("Находим несуществующее животное в БД", () -> {
             given().spec(request)
                    .contentType(JSON)
                    .when()
                    .get("pet/" + noPetId)
                    .then()
                    .spec(unsuccessfulResponse)
                    .body("code", is(1))
                    .body("type", is("error"))
                    .body("message", is("Pet not found"));
        });
    }
}