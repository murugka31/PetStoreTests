package com.emurugova.tests.store;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.entity.request.OrderDataRequest;
import com.emurugova.entity.response.OrderDataResponse;
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
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class AddPurchaseOrder extends TestBase{

    private TestData testData = new TestData();
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

            assertEquals(storeDataRequest.getTestData().orderId, order.getId());
            assertEquals(storeDataRequest.getTestData().orderPetId, order.getPetId());
            assertEquals(storeDataRequest.getTestData().orderQuantity, order.getQuantity());
            assertEquals(storeDataRequest.getTestData().orderStatus, order.getStatus());
            assertEquals(storeDataRequest.getTestData().orderComplete, order.getComplete());
        });
    }
}