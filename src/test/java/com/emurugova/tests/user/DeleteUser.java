package com.emurugova.tests.user;

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

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class DeleteUser extends TestBase {

    @Test
    @AllureId("17552")
    @DisplayName("Удалить пользователя")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("userTest")})
    void deleteExistedUser () {
        String userName = TestData.userName;
        String newUserData = TestData.newUserData;
        step("Добавляем нового пользователя", () -> {
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .body(newUserData)
               .when()
               .post("user/");
        });

        step("Удаляем пользователя", () -> {
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .when()
               .delete("user/"+userName)
               .then()
               .spec(deleteExistedUserResponse);
        });
    }

    @Test
    @AllureId("17553")
    @DisplayName("Удалить несуществующего пользователя")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("userTest")})
    void deleteNoExistedUser () {
        String noUserName = TestData.noUserName;
        step("Удаляем несуществующего пользователя", () -> {
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .when()
               .delete("user/"+noUserName)
               .then()
               .statusCode(404);
        });
    }
}