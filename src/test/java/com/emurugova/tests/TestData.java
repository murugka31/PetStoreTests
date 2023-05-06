package com.emurugova.tests;

import com.github.javafaker.Faker;

public class TestData {

    public static Faker faker = new Faker();

//Pet Data
    public static int petId = faker.number().numberBetween(1110, 1200);
    public static int categoryId = faker.number().numberBetween(1, 5);
    public static String categoryName = faker.animal().name();
    public static String petName = faker.pokemon().name();
    public static String petPhotoUrls = faker.internet().url();
    public static int petTagsId = faker.number().numberBetween(1, 3);
    public static String petTagsName = faker.lordOfTheRings().character();
    public static String[] statuses = { "available", "pending", "sold" };
    public static String petStatus = faker.options().option(statuses);
    public static int noPetId = faker.number().numberBetween(2000, 3000);

//Order Data
    public static int orderId = faker.number().numberBetween(100, 120);
    public static int orderPetId = faker.number().numberBetween(1110, 1200);
    public static int orderQuantity = faker.number().numberBetween(1, 3);
    public static String[] ShipDate = { "2023-02-26T08:47:47.084Z", "2023-03-13T10:47:47.084Z", "2023-04-28T16:47:47.084Z" };
    public static String orderShipDate = faker.options().option(ShipDate);
    public static String orderStatus = "placed";
    public static String orderComplete = "true";
    public static int noPurchaseOrder = faker.number().numberBetween(1000, 1200);

//User Data
    public static int userId = faker.number().numberBetween(1000,1200);
    public static String userName = faker.name().firstName();
    public static String userFirstName = faker.name().firstName();
    public static String userLastName = faker.name().lastName();
    public static String userEmail = faker.internet().emailAddress();
    public static String userPassword = faker.harryPotter().house();
    public static String userPhone = faker.phoneNumber().phoneNumber();
    public static int userStatus = faker.number().numberBetween(1,5);
    public static String noUserName = faker.harryPotter().spell();
}