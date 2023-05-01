package com.emurugova.tests.store;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.specs.Specs.*;
import static com.emurugova.tests.TestData.petId;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class FindPurchaseOrder extends TestBase {

    @Test
    @AllureId("17556")
    @DisplayName("Найти заказ на покупку")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
    void findPurchaseOrderById () {
        int purchaseOrder = TestData.purchaseOrder;
        String newPurchaseOrderData = TestData.newPurchaseOrderData;
        step("Добавляем новый заказ на покупку животного", () -> {
        given().spec(request)
               .body(newPurchaseOrderData)
               .when()
               .post("store/order/");
        });

        step("Находим заказ на покупку животного", () -> {
        given().spec(request)
               .when()
               .get("store/order/"+purchaseOrder)
               .then()
               .spec(successfulResponse)
               .body("status", is("placed"))
               .body("petId", is(petId))
               .body("id", is(purchaseOrder));
        });
    }

    @Test
    @AllureId("17554")
    @DisplayName("Найти несуществующий заказ на покупку")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("storeTest")})
    void findNoPurchaseOrderById () {
        int noPurchaseOrder = TestData.noPurchaseOrder;
        step("Находим несуществующий заказ на покупку животного", () -> {
        given().spec(request)
               .when()
               .get("store/order/"+noPurchaseOrder)
               .then()
               .spec(unsuccessfulResponse)
               .body("code", is(1))
               .body("type", is("error"))
               .body("message", is("Order not found"));
        });
    }
}