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

@Microservice("Swagger Petstore")
@Layer("API")
@Owner("Murugova Elena")
@Feature("DELETE")
public class DeletePurchaseOrder extends TestBase {

    @Test
    @Tags({@Tag("api"), @Tag("critical"), @Tag("storeTest")})
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

    @Test
    @Tags({@Tag("api"), @Tag("normal"), @Tag("storeTest")})
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
