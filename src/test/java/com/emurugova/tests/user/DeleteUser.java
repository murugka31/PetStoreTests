package com.emurugova.tests.user;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.models.User;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.specs.Specs.*;
import static com.emurugova.tests.TestData.faker;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class DeleteUser extends TestBase {

    @Test
    @AllureId("17552")
    @DisplayName("Удалить пользователя")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("userTest")})
    void deleteExistedUser () {
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
               .post("user/");
        });

        step("Удаляем пользователя", () -> {
        given().spec(request)
               .when()
               .delete("user/"+user.username)
               .then()
               .spec(successfulResponse)
               .body("code", is(200))
               .body("type", is("unknown"))
               .body("message", is(user.username));
        });
    }

    @Test
    @AllureId("17553")
    @DisplayName("Удалить несуществующего пользователя")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("userTest")})
    void deleteNoExistedUser () {
        String noUserName = TestData.noUserName;
        step("Удаляем несуществующего пользователя", () -> {
        given().spec(request)
               .when()
               .delete("user/"+noUserName)
               .then()
               .statusCode(404);
        });
    }
}