package com.emurugova.tests;

import com.emurugova.config.MainConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import static java.lang.String.format;

public class TestBase {

    public static MainConfig config = ConfigFactory.create(MainConfig.class, System.getProperties());

    @BeforeAll
    static void setUp() {
        String hostname = config.hostname();
        RestAssured.baseURI = format("https://%s", hostname);;
    }
}
