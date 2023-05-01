package com.emurugova.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.emurugova.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;

public class Specs {

    public static RequestSpecification request = with()
            .contentType(ContentType.JSON)
            .filter(customLogFilter().withCustomTemplates());

    public static ResponseSpecification successfulResponse = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification unsuccessfulResponse = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(404)
            .build();
}