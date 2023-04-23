package com.emurugova.tests;

public class TestData {

    public static int petId = 1111;
    public static int noPetId = 111100000;
    public static int categoryId = 1;
    public static String categoryName = "dog";
    public static String petName = "Loki";
    public static String petPhotoUrls = "https://zooclub.ru/attach/10000/10091.jpg";
    public static int petTagsId = 1;
    public static String petTagsName = "no name";
    public static String petStatus = "available";
    public static String newPetData = "{ \"id\": "+petId+", \"category\": { \"id\": "+categoryId+", \"name\": \""+categoryName+"\" }, " +
            "\"name\": \""+petName+"\", \"photoUrls\": [ \""+petPhotoUrls+"\" ], \"tags\": [ { \"id\": "+petTagsId+", " +
            "\"name\": \""+petTagsName+"\" } ], \"status\": \""+petStatus+"\" }";

    public static int purchaseOrder = 100;
    public static int noPurchaseOrder = 3;
    public static String newPurchaseOrderData = "{ \"id\": "+purchaseOrder+", \"petId\": "+petId+", \"quantity\": 0, " +
            "\"shipDate\": \"2023-04-16T07:42:39.059Z\", \"status\": \"placed\", \"complete\": true }";

    public static int userId = 1114;
    public static String userName = "UserJohny";
    public static String noUserName = "UserJohny2";
    public static String userFirstName = "John";
    public static String userLastName = "Johnas";
    public static String userEmail = "mail@mail.ru";
    public static String userPassword = "12345";
    public static String userPhone = "89999999999";
    public static int userStatus = 1;
    public static String newUserData = "{ \"id\": "+userId+", \"username\": \""+userName+"\", \"firstName\": \""+userFirstName+"\", " +
            "\"lastName\": \""+userLastName+"\", \"email\": \""+userEmail+"\", \"password\": \""+userPassword+"\", \"phone\": \""+userPhone+"\", \"userStatus\": "+userStatus+"}";
}
