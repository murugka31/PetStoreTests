package com.emurugova.tests.store;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.OrderDataRequest;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class DeletePurchaseOrder extends TestBase {

    private TestData testData = new TestData();
    private OrderDataRequest storeDataRequest = new OrderDataRequest();

    @Test
    @AllureId("17559")
    @DisplayName("Delete purchase order by ID")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
    void deleteExistedPurchaseOrder () {
        step("Добавляем новый заказ на покупку животного", () -> {
        given().spec(request)
               .body(storeDataRequest.orderRequest().toString())
               .when()
               .post("store/order/");
        });

        step("Удаляем заказ на покупку животного", () -> {
        given().spec(request)
               .when()
               .delete("store/order/"+storeDataRequest.getTestData().orderId)
               .then()
               .spec(successfulResponse())
               .body("code", is(200))
               .body("type", is("unknown"));
        });
    }

    @Test
    @AllureId("17557")
    @DisplayName("Delete non-existent purchase order by ID")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("storeTest")})
    void deleteNoExistedPurchaseOrder () {
        step("Удаляем несуществующий заказ на покупку животного", () -> {
        given().spec(request)
               .when()
               .delete("store/order/"+testData.noPurchaseOrder)
               .then()
               .spec(response(404) )
               .body("code", is(404))
               .body("type", is("unknown"))
               .body("message", is("Order Not Found"));
        });
    }
}