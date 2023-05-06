package com.emurugova.tests.user;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.UserDataRequest;
import com.emurugova.entity.response.UserDataResponse;
import com.emurugova.tests.TestBase;
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

    private UserDataRequest userDataRequest = new UserDataRequest();

    @Test
    @AllureId("17551")
    @DisplayName("Get user by user name")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("userTest")})
    void getUserByName() {
        step("Добавляем нового пользователя в БД", () -> {
            given().spec(request)
                   .body(userDataRequest.userRequest().toString())
                   .when()
                   .post("user/");
        });

        step("Находим пользователя в БД", () -> {
            UserDataResponse user =
             given()
                   .spec(request)
                   .when()
                   .get("user/" + userName)
                   .then()
                   .extract().as(UserDataResponse.class);

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