package com.emurugova.tests.user;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.UserDataRequest;
import com.emurugova.tests.TestBase;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.specs.Specs.request;
import static com.emurugova.specs.Specs.successfulResponse;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class CreateUser extends TestBase {

    private UserDataRequest userDataRequest = new UserDataRequest();

    @Test
    @AllureId("17555")
    @DisplayName("Create user")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("userTest")})
    void createNewUserTest () {
        step("Добавляем нового пользователя", () -> {
        given().spec(request)
               .body(userDataRequest.userRequest().toString())
               .when()
               .post("user/")
               .then()
               .spec(successfulResponse())
               .body("code", is(200))
               .body("type", is("unknown"));
        });
    }
}