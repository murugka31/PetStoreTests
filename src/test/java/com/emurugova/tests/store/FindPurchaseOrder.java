package com.emurugova.tests.store;

import com.emurugova.tests.TestBase;
import com.emurugova.tests.TestData;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static com.emurugova.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class FindPurchaseOrder extends TestBase {

    @Tag("storeTest")
    @Test
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

    @Tag("storeTest")
    @Test
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

