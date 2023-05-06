package com.emurugova.entity.request;

import com.emurugova.tests.TestData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class PetDataRequest {

    private JsonObject petData = new JsonObject();
    private TestData testData = new TestData();

    public JsonObject petRequest() {
        petData.addProperty("id", testData.petId);

        JsonObject category = new JsonObject();
        category.addProperty("id", testData.categoryId);
        category.addProperty("name", testData.categoryName);
        petData.add("category", category);

        petData.addProperty("name", testData.petName);

        JsonArray photoUrls = new JsonArray();
        photoUrls.add(testData.petPhotoUrls);
        petData.add("photoUrls", photoUrls);

        JsonArray tags = new JsonArray();
        JsonObject tag = new JsonObject();
        tag.addProperty("id", testData.petTagsId);
        tag.addProperty("name", testData.petTagsName);
        tags.add(tag);
        petData.add("tags", tags);

        petData.addProperty("status",testData.petStatus);
        return petData;
    }
}