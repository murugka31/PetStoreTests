package com.emurugova.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

import static com.emurugova.tests.TestData.*;
import static org.hamcrest.Matchers.is;

public class Specs {

    public static ResponseSpecification petResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification noExistedPetResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(404)
            .expectBody("code", is(1))
            .expectBody("type", is("error"))
            .expectBody("message", is("Pet not found"))
            .build();

    public static ResponseSpecification deletePetResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification findPurchaseOrderResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody("petId", is(petId))
            .expectBody("id", is(purchaseOrder))
            .build();

    public static ResponseSpecification findNoPurchaseOrderResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(404)
            .expectBody("code", is(1))
            .expectBody("type", is("error"))
            .expectBody("message", is("Order not found"))
            .build();

    public static ResponseSpecification deletePurchaseOrderResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody("code", is(200))
            .expectBody("type", is("unknown"))
            .build();

    public static ResponseSpecification deleteNoPurchaseOrderResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(404)
            .expectBody("code", is(404))
            .expectBody("type", is("unknown"))
            .expectBody("message", is("Order Not Found"))
            .build();

    public static ResponseSpecification createNewUserResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody("code", is(200))
            .expectBody("type", is("unknown"))
            .build();

    public static ResponseSpecification deleteExistedUserResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody("code", is(200))
            .expectBody("type", is("unknown"))
            .expectBody("message", is(userName))
            .build();

}
