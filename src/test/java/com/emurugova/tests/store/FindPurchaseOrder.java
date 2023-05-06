package com.emurugova.tests.store;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.OrderDataRequest;
import com.emurugova.tests.TestBase;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.specs.Specs.*;
import static com.emurugova.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class FindPurchaseOrder extends TestBase {

    private OrderDataRequest storeDataRequest = new OrderDataRequest();

    @Test
    @AllureId("17556")
    @DisplayName("Find purchase order by ID")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
    void findPurchaseOrderById () {
        step("Добавляем новый заказ на покупку животного", () -> {
        given().spec(request)
               .body(storeDataRequest.orderRequest().toString())
               .when()
               .post("store/order/");
        });

        step("Находим заказ на покупку животного", () -> {
        given().spec(request)
               .when()
               .get("store/order/"+orderId)
               .then()
               .spec(successfulResponse())
               .body("status", is("placed"))
               .body("petId", is(orderPetId ))
               .body("id", is(orderId));
        });
    }

    @Test
    @AllureId("17554")
    @DisplayName("Find non-existent purchase order by ID")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("storeTest")})
    void findNoPurchaseOrderById () {
        step("Находим несуществующий заказ на покупку животного", () -> {
        given().spec(request)
               .when()
               .get("store/order/"+noPurchaseOrder)
               .then()
               .spec(response(404))
               .body("code", is(1))
               .body("type", is("error"))
               .body("message", is("Order not found"));
        });
    }
}