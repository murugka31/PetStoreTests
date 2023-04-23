package com.emurugova.tests.user;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.models.User;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.tests.TestData.*;
import static com.emurugova.tests.TestData.userStatus;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class GetUser extends TestBase {

    @Test
    @DisplayName("Найти пользователя по имени")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("userTest")})
    void getUserByName() {
        String newUserData = TestData.newUserData;
        step("Create an user", () -> {
        given().filter(customLogFilter().withCustomTemplates()).contentType(JSON)
               .body(newUserData)
               .when()
               .post("user/");
        });

        step("Find an user", () -> {
        User user = given().contentType(JSON)
                .when()
                .get("user/" + userName)
                .then()
                .log().body()
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
