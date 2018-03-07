package com.marik.electronicslist.util;

import android.content.Context;

import com.google.gson.Gson;

import com.marik.electronicslist.model.CategoryModel;
import com.marik.electronicslist.model.MainCategoryModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FetchData {

    //Method that will parse the JSON file and will return a JSONObject
    public static List<CategoryModel> loadJSONFromAsset(Context context, String language) {
        MainCategoryModel electronics = null;
        try {
            String jsonFileName = "";

            if (language.equals("en")) {
                jsonFileName = "electronics.json";
            } else if (language.equals("hy")) {
                jsonFileName = "electronics_arm.json";
            } else if (language.equals("ru")) {
                jsonFileName = "electronics_ru.json";
            }

            InputStream is = context.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            electronics = new Gson().fromJson(json, MainCategoryModel.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        if (electronics != null) {
            return electronics.category;
        } else {
            return new ArrayList<>();
        }
    }
}
