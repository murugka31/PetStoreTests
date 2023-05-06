package com.emurugova.tests.pet;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.tests.TestBase;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.emurugova.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class GetPetListTest extends TestBase {

    @ParameterizedTest
    @ValueSource(strings = {
            "available",
            "pending",
            "sold"})
    @AllureId("17561")
    @DisplayName("Find pet by status")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void getAvailablePetsListTest(String status) {
        step("Находим всех животных по статусe", () -> {
        given().spec(request)
                .when()
                .get("pet/findByStatus?status=" + status)
                .then()
                .spec(successfulResponse());
        });
    }
}