package com.emurugova.tests.store;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.models.Order;
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

    @Test
    @AllureId("17558")
    @DisplayName("Добавить заказ на покупку")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
    void addOrderTest () {
        Order order = new Order();
              order.id = faker.number().numberBetween(100, 120);
              order.petId = faker.number().numberBetween(1110, 1200);
              order.quantity = faker.number().numberBetween(1, 3);
              order.shipDate = "2023-04-16T07:42:39.059Z";
              order.status = "placed";
              order.complete = "true";
        step("Добавляем новый заказ на покупку животного", () -> {
        Order addedOrder = given()
                .spec(request)
                .body(order)
                .when()
                .post("store/order/")
                .then()
                .spec(successfulResponse)
                .extract().as(Order.class);

        assertEquals(order.id, addedOrder.getId());
        assertEquals(order.petId, addedOrder.getPetId());
        });
    }
}