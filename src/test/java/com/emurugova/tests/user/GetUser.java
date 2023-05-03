package com.emurugova.tests.user;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.models.User;
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

    @Test
    @AllureId("17551")
    @DisplayName("Найти пользователя по имени")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("userTest")})
    void getUserByName() {
        User user = new User();
             user.id= faker.number().numberBetween(1000,1200);
             user.username = faker.name().firstName();
             user.firstName = faker.name().firstName();
             user.lastName = faker.name().lastName();
             user.email = faker.internet().emailAddress();
             user.password = faker.harryPotter().house();
             user.phone = faker.phoneNumber().phoneNumber();
             user.userStatus = faker.number().numberBetween(1,5);
        step("Добавляем нового пользователя в БД", () -> {
            given().spec(request)
                   .body(user)
                   .when()
                   .post("user/");
        });

        step("Находим пользователя в БД", () -> {
            User foundUser = given()
                   .spec(request)
                   .when()
                   .get("user/" + user.username)
                   .then()
                   .extract().as(User.class);

            assertEquals(user.id, foundUser.getId());
            assertEquals(user.username , foundUser.getUsername());
            assertEquals(user.firstName, foundUser.getFirstName());
            assertEquals(user.lastName, foundUser.getLastName());
            assertEquals(user.email, foundUser.getEmail());
            assertEquals(user.password, foundUser.getPassword());
            assertEquals(user.phone, foundUser.getPhone());
            assertEquals(user.userStatus, foundUser.getUserStatus());
        });
    }
}