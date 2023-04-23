package com.emurugova.tests.user;

import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.createNewUserResponse;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class CreateUser extends TestBase {

    @Tag("userTest")
    @Test
    void createNewUserTest () {
        String newUserData = TestData.newUserData;
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .body(newUserData)
               .when()
               .post("user/")
               .then()
               .spec(createNewUserResponse);
    }
}
