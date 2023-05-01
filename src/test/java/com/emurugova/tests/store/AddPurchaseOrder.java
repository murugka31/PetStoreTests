package com.emurugova.tests.store;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.models.Order;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class AddPurchaseOrder extends TestBase{

    @Test
    @AllureId("17558")
    @DisplayName("Добавить заказ на покупку")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
    void addOrderTest () {
        String newPurchaseOrderData = TestData.newPurchaseOrderData;
        step("Добавляем новый заказ на покупку животного", () -> {
        Order order = given()
                .spec(request)
                .body(newPurchaseOrderData)
                .when()
                .post("store/order/")
                .then()
                .spec(successfulResponse)
                .extract().as(Order.class);

        assertEquals(purchaseOrder, order.getId());
        assertEquals(petId, order.getPetId());
        });
    }
}