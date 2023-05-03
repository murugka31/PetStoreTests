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

import static com.emurugova.specs.Specs.*;
import static com.emurugova.tests.TestData.faker;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
public class DeletePurchaseOrder extends TestBase {

    @Test
    @AllureId("17559")
    @DisplayName("Удалить заказ на покупку")
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
    void deleteExistedPurchaseOrder () {
        Order order = new Order();
              order.id = faker.number().numberBetween(100, 120);
              order.petId = faker.number().numberBetween(1110, 1200);
              order.quantity = faker.number().numberBetween(1, 3);
              order.shipDate = faker.date().toString();
              order.status = "placed";
              order.complete = "true";
        step("Добавляем новый заказ на покупку животного", () -> {
        given().spec(request)
               .body(order)
               .when()
               .post("store/order/");
        });

        step("Удаляем заказ на покупку животного", () -> {
        given().spec(request)
               .when()
               .delete("store/order/"+order.id)
               .then()
               .spec(successfulResponse)
               .body("code", is(200))
               .body("type", is("unknown"));
        });
    }

    @Test
    @AllureId("17557")
    @DisplayName("Удалить несуществующий заказ на покупку")
    @Tags({@Tag("api"), @Tag("normal"), @Tag("storeTest")})
    void deleteNoExistedPurchaseOrder () {
        int noPurchaseOrder = TestData.noPurchaseOrder;
        step("Удаляем несуществующий заказ на покупку животного", () -> {
        given().spec(request)
               .when()
               .delete("store/order/"+noPurchaseOrder)
               .then()
               .spec(unsuccessfulResponse )
               .body("code", is(404))
               .body("type", is("unknown"))
               .body("message", is("Order Not Found"));
        });
    }
}