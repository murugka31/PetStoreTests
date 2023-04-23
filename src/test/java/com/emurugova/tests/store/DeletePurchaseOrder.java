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

public class DeletePurchaseOrder extends TestBase {

    @Tag("storeTest")
    @Test
    void deleteExistedPurchaseOrder () {
        int purchaseOrder = TestData.purchaseOrder;
        String newPurchaseOrderData = TestData.newPurchaseOrderData;
        step("Create a purchase order", () -> {
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .body(newPurchaseOrderData)
               .when()
               .post("store/order/");
        });

        step("Delete a purchase order", () -> {
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .when()
               .delete("store/order/"+purchaseOrder)
               .then()
               .spec(deletePurchaseOrderResponse);
        });
    }

    @Tag("storeTest")
    @Test
    void deleteNoExistedPurchaseOrder () {
        int noPurchaseOrder = TestData.noPurchaseOrder;
        given().filter(customLogFilter().withCustomTemplates())
               .contentType(JSON)
               .when()
               .delete("store/order/"+noPurchaseOrder)
               .then()
               .spec(deleteNoPurchaseOrderResponse);
    }
}
