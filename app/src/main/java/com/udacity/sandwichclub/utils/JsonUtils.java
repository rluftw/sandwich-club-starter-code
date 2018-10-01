package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;

        try {
            JSONObject sandwichJSONResponse = new JSONObject(json);
            JSONObject sandwichNameJSON = sandwichJSONResponse.getJSONObject("name");
            String mainName = sandwichNameJSON.getString("mainName");

            JSONArray sandwichAliasArray = sandwichNameJSON.getJSONArray("alsoKnownAs");
            JSONArray ingredientsArray = sandwichJSONResponse.getJSONArray("ingredients");


            List<String> sandwichAliasList = new ArrayList<>();
            for (int i = 0; i < sandwichAliasArray.length(); i++) {
                sandwichAliasList.add(sandwichAliasArray.getString(i));
            }

            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }

            String placeOfOrigin = sandwichJSONResponse.getString("placeOfOrigin");
            String description = sandwichJSONResponse.getString("description");
            String image = sandwichJSONResponse.getString("image");

            sandwich = new Sandwich(mainName, sandwichAliasList, placeOfOrigin, description, image, ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
