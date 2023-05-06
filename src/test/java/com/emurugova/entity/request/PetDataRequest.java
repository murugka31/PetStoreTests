package com.emurugova.entity.request;

import com.emurugova.tests.TestData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import static com.emurugova.tests.TestData.*;

public class PetDataRequest {

    private JsonObject petData = new JsonObject();
    private TestData testData = new TestData();

    public JsonObject petRequest() {
        petData.addProperty("id", petId);

        JsonObject category = new JsonObject();
        category.addProperty("id", categoryId);
        category.addProperty("name", categoryName);
        petData.add("category", category);

        petData.addProperty("name", petName);

        JsonArray photoUrls = new JsonArray();
        photoUrls.add(petPhotoUrls);
        petData.add("photoUrls", photoUrls);

        JsonArray tags = new JsonArray();
        JsonObject tag = new JsonObject();
        tag.addProperty("id", petTagsId);
        tag.addProperty("name", petTagsName);
        tags.add(tag);
        petData.add("tags", tags);

        petData.addProperty("status", petStatus);
        return petData;
    }
}