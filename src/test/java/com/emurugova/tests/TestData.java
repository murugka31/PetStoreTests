package com.emurugova.tests;

import com.github.javafaker.Faker;

public class TestData {

    public static Faker faker = new Faker();

    public static int petId = faker.number().numberBetween(1110,1200);
    public static int noPetId = faker.number().numberBetween(2000,3000);;
    public static int categoryId = faker.number().numberBetween(1,5);
    public static String categoryName = faker.animal().name();
    public static String petName = faker.pokemon().name();
    public static String petPhotoUrls = "https://zooclub.ru/attach/10000/10091.jpg";
    public static int petTagsId = faker.number().numberBetween(1,3);;
    public static String petTagsName = faker.lordOfTheRings().character();
    public static String petStatus =faker.dog().breed();
    public static String newPetData = "{ \"id\": "+petId+", \"category\": { \"id\": "+categoryId+", \"name\": \""+categoryName+"\" }, " +
            "\"name\": \""+petName+"\", \"photoUrls\": [ \""+petPhotoUrls+"\" ], \"tags\": [ { \"id\": "+petTagsId+", " +
            "\"name\": \""+petTagsName+"\" } ], \"status\": \""+petStatus+"\" }";

    public static int purchaseOrder = faker.number().numberBetween(100,120);
    public static int noPurchaseOrder = faker.number().numberBetween(1000,1200);;
    public static String newPurchaseOrderData = "{ \"id\": "+purchaseOrder+", \"petId\": "+petId+", \"quantity\": 0, " +
            "\"shipDate\": \"2023-04-16T07:42:39.059Z\", \"status\": \"placed\", \"complete\": true }";

    public static int userId = faker.number().numberBetween(1000,1200);
    public static String userName = faker.name().firstName();
    public static String noUserName = faker.harryPotter().spell();
    public static String userFirstName = faker.name().firstName();
    public static String userLastName = faker.name().lastName();
    public static String userEmail = faker.internet().emailAddress();
    public static String userPassword = faker.harryPotter().house();
    public static String userPhone = faker.phoneNumber().phoneNumber();
    public static int userStatus = faker.number().numberBetween(1,5);;
    public static String newUserData = "{ \"id\": "+userId+", \"username\": \""+userName+"\", \"firstName\": \""+userFirstName+"\", " +
            "\"lastName\": \""+userLastName+"\", \"email\": \""+userEmail+"\", \"password\": \""+userPassword+"\", \"phone\": \""+userPhone+"\", \"userStatus\": "+userStatus+"}";
}
