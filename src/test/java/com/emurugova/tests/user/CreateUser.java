package com.emurugova.tests.user;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.models.User;
import com.emurugova.tests.TestBase;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.specs.Specs.request;
import static com.emurugova.specs.Specs.successfulResponse;
import static com.emurugova.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class CreateUser extends TestBase {

    @Test
    @AllureId("17555")
    @DisplayName("Создать нового пользователя")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("userTest")})
    void createNewUserTest () {
        User user = new User();
             user.id= faker.number().numberBetween(1000,1200);
             user.username = faker.name().firstName();
             user.firstName = faker.name().firstName();
             user.lastName = faker.name().lastName();
             user.email = faker.internet().emailAddress();
             user.password = faker.harryPotter().house();
             user.phone = faker.phoneNumber().phoneNumber();
             user.userStatus = faker.number().numberBetween(1,5);

        step("Добавляем нового пользователя", () -> {
        given().spec(request)
               .body(user)
               .when()
               .post("user/")
               .then()
               .spec(successfulResponse)
               .body("code", is(200))
               .body("type", is("unknown"));
        });
    }
}