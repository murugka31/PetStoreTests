package com.emurugova.tests;

import com.github.javafaker.Faker;

public class TestData {

    public static Faker faker = new Faker();

    public static int petId = faker.number().numberBetween(1110, 1200);
    public static int categoryId = faker.number().numberBetween(1, 5);
    public static String categoryName = faker.animal().name();
    public static String petName = faker.pokemon().name();
    public static String petPhotoUrls = faker.internet().url();
    public static int petTagsId = faker.number().numberBetween(1, 3);
    public static String petTagsName = faker.lordOfTheRings().character();
    public static String petStatus = faker.dog().breed();

    public static String newPetData = "{ \"id\": " + petId + ", \"category\": { \"id\": " + categoryId + ", \"name\": \"" + categoryName + "\" }, " +
            "\"name\": \"" + petName + "\", \"photoUrls\": [ \"" + petPhotoUrls + "\" ], \"tags\": [ { \"id\": " + petTagsId + ", " +
            "\"name\": \"" + petTagsName + "\" } ], \"status\": \"" + petStatus + "\" }";

    public static int noPetId = faker.number().numberBetween(2000, 3000);
    public static int noPurchaseOrder = faker.number().numberBetween(1000, 1200);
    public static String noUserName = faker.harryPotter().spell();

}