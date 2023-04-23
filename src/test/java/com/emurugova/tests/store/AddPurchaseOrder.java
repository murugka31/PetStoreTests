package com.emurugova.tests.store;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.models.Order;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.tests.TestData.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class AddPurchaseOrder extends TestBase{

    @Test
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
    void addOrderTest () {
        String newPurchaseOrderData = TestData.newPurchaseOrderData;
        Order order = given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType(JSON)
                .body(newPurchaseOrderData)
                .when()
                .post("store/order/")
                .then()
                .log().body()
                .extract().as(Order.class);

        assertEquals(purchaseOrder, order.getId());
        assertEquals(petId, order.getPetId());
    }
}
