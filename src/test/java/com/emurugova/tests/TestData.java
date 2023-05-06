package com.emurugova.tests;

import com.github.javafaker.Faker;

public class TestData {

    public static Faker faker = new Faker();

//Pet Data
    public int petId = faker.number().numberBetween(1110, 1200);
    public int categoryId = faker.number().numberBetween(1, 5);
    public String categoryName = faker.animal().name();
    public String petName = faker.pokemon().name();
    public String petPhotoUrls = faker.internet().url();
    public int petTagsId = faker.number().numberBetween(1, 3);
    public String petTagsName = faker.lordOfTheRings().character();
    public String[] statuses = { "available", "pending", "sold" };
    public String petStatus = faker.options().option(statuses);
    public int noPetId = faker.number().numberBetween(2000, 3000);

//Order Data
    public int orderId = faker.number().numberBetween(100, 120);
    public int orderPetId = faker.number().numberBetween(1110, 1200);
    public int orderQuantity = faker.number().numberBetween(1, 3);
    public String[] ShipDate = { "2023-02-26T08:47:47.084Z", "2023-03-13T10:47:47.084Z", "2023-04-28T16:47:47.084Z" };
    public String orderShipDate = faker.options().option(ShipDate);
    public String orderStatus = "placed";
    public String orderComplete = "true";
    public int noPurchaseOrder = faker.number().numberBetween(1000, 1200);

//User Data
    public int userId = faker.number().numberBetween(1000,1200);
    public String userName = faker.name().firstName();
    public String userFirstName = faker.name().firstName();
    public String userLastName = faker.name().lastName();
    public String userEmail = faker.internet().emailAddress();
    public String userPassword = faker.harryPotter().house();
    public String userPhone = faker.phoneNumber().phoneNumber();
    public int userStatus = faker.number().numberBetween(1,5);
    public String noUserName = faker.harryPotter().spell();
}