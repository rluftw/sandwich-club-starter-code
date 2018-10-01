package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;

        try {
            JSONObject sandwichJSONResponse = new JSONObject(json);
            JSONObject sandwichNameJSON = sandwichJSONResponse.getJSONObject("name");
            String mainName = sandwichNameJSON.getString("mainName");

            List<String> sandwichAliases = null;
            List<String> ingredients = null;

            if (sandwichNameJSON.getJSONArray("alsoKnownAs") instanceof List &&
                    sandwichJSONResponse.getJSONArray("ingredients") instanceof List) {
                sandwichAliases = (List<String>) sandwichNameJSON.getJSONArray("alsoKnownAs");
                ingredients = (List<String>) sandwichJSONResponse.getJSONArray("ingredients");
            }

            String placeOfOrigin = sandwichJSONResponse.getString("placeOfOrigin");
            String description = sandwichJSONResponse.getString("description");
            String image = sandwichJSONResponse.getString("image");

            sandwich = new Sandwich(mainName, sandwichAliases, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
