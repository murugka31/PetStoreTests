package com.emurugova.tests.user;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.UserDataRequest;
import com.emurugova.entity.response.UserDataResponse;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import static com.emurugova.specs.Specs.request;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class GetUser extends TestBase {

    private TestData testData = new TestData();
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
                   .get("user/" + userDataRequest.getTestData().userName)
                   .then()
                   .extract().as(UserDataResponse.class);

            assertEquals(userDataRequest.getTestData().userId, user.getId());
            assertEquals(userDataRequest.getTestData().userName, user.getUsername());
            assertEquals(userDataRequest.getTestData().userFirstName, user.getFirstName());
            assertEquals(userDataRequest.getTestData().userLastName, user.getLastName());
            assertEquals(userDataRequest.getTestData().userEmail, user.getEmail());
            assertEquals(userDataRequest.getTestData().userPassword, user.getPassword());
            assertEquals(userDataRequest.getTestData().userPhone, user.getPhone());
            assertEquals(userDataRequest.getTestData().userStatus, user.getUserStatus());
        });
    }
}