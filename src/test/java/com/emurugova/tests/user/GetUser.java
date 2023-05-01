package com.emurugova.tests.user;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.models.User;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import static com.emurugova.specs.Specs.request;
import static com.emurugova.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class GetUser extends TestBase {

    @Test
    @AllureId("17551")
    @DisplayName("Найти пользователя по имени")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("userTest")})
    void getUserByName() {
        String newUserData = TestData.newUserData;
        step("Добавляем нового пользователя в БД", () -> {
            given().spec(request)
                   .body(newUserData)
                   .when()
                   .post("user/");
        });

        step("Находим пользователя в БД", () -> {
            User user = given()
                   .spec(request)
                   .when()
                   .get("user/" + userName)
                   .then()
                   .extract().as(User.class);

            assertEquals(userId, user.getId());
            assertEquals(userName, user.getUsername());
            assertEquals(userFirstName, user.getFirstName());
            assertEquals(userLastName, user.getLastName());
            assertEquals(userEmail, user.getEmail());
            assertEquals(userPassword, user.getPassword());
            assertEquals(userPhone, user.getPhone());
            assertEquals(userStatus, user.getUserStatus());
        });
    }
}