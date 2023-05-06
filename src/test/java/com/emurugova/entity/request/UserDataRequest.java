package com.emurugova.entity.request;

import com.emurugova.tests.TestData;
import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class UserDataRequest {

    private JsonObject userData = new JsonObject();
    private TestData testData = new TestData();

    public JsonObject userRequest() {
        userData.addProperty("id", testData.userId);
        userData.addProperty("username", testData.userName);
        userData.addProperty("firstName", testData.userFirstName);
        userData.addProperty("lastName", testData.userLastName);
        userData.addProperty("email", testData.userEmail);
        userData.addProperty("password", testData.userPassword);
        userData.addProperty("phone", testData.userPhone);
        userData.addProperty("userStatus", testData.userStatus);
        return userData;
    }
}