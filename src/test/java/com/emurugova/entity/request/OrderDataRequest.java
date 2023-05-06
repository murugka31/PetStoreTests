package com.emurugova.entity.request;

import com.emurugova.tests.TestData;
import com.google.gson.JsonObject;

import static com.emurugova.tests.TestData.*;

public class OrderDataRequest {

    private JsonObject orderData = new JsonObject();
    private TestData testData = new TestData();

    public JsonObject orderRequest() {
        orderData.addProperty("id", orderId);
        orderData.addProperty("petId", orderPetId);
        orderData.addProperty("quantity", orderQuantity);
        orderData.addProperty("shipDate", orderShipDate);
        orderData.addProperty("status", orderStatus);
        orderData.addProperty("complete", orderComplete);
        return orderData;
    }
}