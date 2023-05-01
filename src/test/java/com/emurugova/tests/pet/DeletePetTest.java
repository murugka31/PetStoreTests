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
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class DeletePetTest extends TestBase {

    @Test
    @AllureId("17566")
    @DisplayName("Удалить животное из базы данных по ID")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("petTest")})
    void deletePetTest() {
        Integer petId = TestData.petId;
        String newPetData = TestData.newPetData;
        step("Добавляем животное в БД", () -> {
             given().spec(request)
                    .contentType(JSON)
                    .body(newPetData)
                    .when()
                    .post("pet/");
        });

        step("Удаляем животное из БД", () -> {
             given().spec(request)
                    .when()
                    .delete("pet/" + petId)
                    .then()
                    .spec(successfulResponse);
        });
    }

    @Test
    @AllureId("17567 ")
    @DisplayName("Удалить несуществующее животное из базы данных")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void deleteNoExistedPetTest() {
        Integer noPetId = TestData.noPetId;
        step("Удаляем несуществующее животное из БД", () -> {
             given().spec(request)
                    .when()
                    .delete("pet/" + noPetId)
                    .then()
                    .statusCode(404);
        });
    }
}