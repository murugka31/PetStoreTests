package com.emurugova.entity.request;

import com.emurugova.tests.TestData;
import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class OrderDataRequest {

    private JsonObject orderData = new JsonObject();
    private TestData testData = new TestData();

    public JsonObject orderRequest() {
        orderData.addProperty("id", testData.orderId);
        orderData.addProperty("petId", testData.orderPetId);
        orderData.addProperty("quantity", testData.orderQuantity);
        orderData.addProperty("shipDate", testData.orderShipDate);
        orderData.addProperty("status", testData.orderStatus);
        orderData.addProperty("complete", testData.orderComplete);
        return orderData;
    }
}