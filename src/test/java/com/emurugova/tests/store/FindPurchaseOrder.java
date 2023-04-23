package com.emurugova.tests.store;

import com.emurugova.allure.Layer;
import com.emurugova.allure.Microservice;
import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
@Feature("GET")
public class FindPurchaseOrder extends TestBase {

    @Test
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
    void findPurchaseOrderById () {
        int purchaseOrder = TestData.purchaseOrder;
        String newPurchaseOrderData = TestData.newPurchaseOrderData;
        step("Create a purchase order", () -> {
        given().filter(customLogFilter().withCustomTemplates())
                .contentType(JSON)
                .body(newPurchaseOrderData)
                .when()
                .post("store/order/");
        });

        step("Find a purchase order", () -> {
        given().filter(customLogFilter().withCustomTemplates())
                .contentType(JSON)
                .when()
                .get("store/order/"+purchaseOrder)
                .then()
                .spec(findPurchaseOrderResponse)
                .body("status", is("placed"));
        });
    }

    @Test
    @Tags({@Tag("api"), @Tag("normal"), @Tag("storeTest")})
    void findNoPurchaseOrderById () {
        int noPurchaseOrder = TestData.noPurchaseOrder;
        given().filter(customLogFilter().withCustomTemplates())
                .contentType(JSON)
                .when()
                .get("store/order/"+noPurchaseOrder)
                .then()
                .spec(findNoPurchaseOrderResponse);
    }
}

