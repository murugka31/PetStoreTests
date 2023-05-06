package com.emurugova.tests.store;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.OrderDataRequest;
import com.emurugova.entity.response.OrderDataResponse;
import com.emurugova.tests.TestBase;
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

    private OrderDataRequest storeDataRequest = new OrderDataRequest();

    @Test
    @AllureId("17558")
    @DisplayName("Place an order for a pet")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
    void addOrderTest () {
        step("Добавляем новый заказ на покупку животного", () -> {
        OrderDataResponse order = given()
                .spec(request)
                .body(storeDataRequest.orderRequest().toString())
                .when()
                .post("store/order/")
                .then()
                .spec(successfulResponse())
                .extract().as(OrderDataResponse.class);

            assertEquals(orderId, order.getId());
            assertEquals(orderPetId, order.getPetId());
            assertEquals(orderQuantity, order.getQuantity());
            assertEquals(orderStatus, order.getStatus());
            assertEquals(orderComplete, order.getComplete());
        });
    }
}