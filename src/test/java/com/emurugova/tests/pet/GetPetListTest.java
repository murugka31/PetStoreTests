package com.emurugova.tests.pet;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.tests.TestBase;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.petResponse;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class GetPetListTest extends TestBase {

    @Test
    @AllureId("17561")
    @DisplayName("Вывести список доступных животных")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void getAvailablePetsListTest() {
        String status = "available";
        step("Находим всех животных со статусом Доступны", () -> {
        given().filter(customLogFilter().withCustomTemplates())
                .when()
                .get("pet/findByStatus?status=" + status)
                .then()
                .spec(petResponse);
        });
    }

    @Test
    @AllureId("17562")
    @DisplayName("Вывести список ожидающих животных")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void getPendingPetsListTest() {
        String status = "pending";
        step("Находим всех животных со статусом Ожидают", () -> {
            given().filter(customLogFilter().withCustomTemplates())
                    .contentType(JSON)
                    .when()
                    .get("pet/findByStatus?status=" + status)
                    .then()
                    .spec(petResponse);
        });
    }

    @Test
    @AllureId("17560")
    @DisplayName("Вывести список проданных животных")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("petTest")})
    void getSoldPetsListTest() {
        String status = "sold";
        step("Находим всех животных со статусом Проданы", () -> {
            given().filter(customLogFilter().withCustomTemplates())
                    .contentType(JSON)
                    .when()
                    .get("pet/findByStatus?status=" + status)
                    .then()
                    .spec(petResponse);
        });
    }
}