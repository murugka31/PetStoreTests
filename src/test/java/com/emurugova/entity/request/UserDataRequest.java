package com.emurugova.entity.request;

import com.emurugova.tests.TestData;
import com.google.gson.JsonObject;

import static com.emurugova.tests.TestData.*;

public class UserDataRequest {

    private JsonObject userData = new JsonObject();
    private TestData testData = new TestData();

    public JsonObject userRequest() {
        userData.addProperty("id", userId);
        userData.addProperty("username", userName);
        userData.addProperty("firstName", userFirstName);
        userData.addProperty("lastName", userLastName);
        userData.addProperty("email", userEmail);
        userData.addProperty("password", userPassword);
        userData.addProperty("phone", userPhone);
        userData.addProperty("userStatus", userStatus);
        return userData;
    }
}