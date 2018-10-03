package com.udacity.sandwichclub.utils;
import android.content.Context;
import android.content.res.Resources;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json, Context context) {
        Sandwich sandwich = null;

        try {
            Resources res = context.getResources();
            JSONObject sandwichJSONResponse = new JSONObject(json);
            JSONObject sandwichNameJSON = sandwichJSONResponse.getJSONObject(res.getString(R.string.name));
            String mainName = sandwichNameJSON.getString(res.getString(R.string.mainName));

            JSONArray sandwichAliasArray = sandwichNameJSON.getJSONArray(res.getString(R.string.alsoKnownAs));
            JSONArray ingredientsArray = sandwichJSONResponse.getJSONArray(res.getString(R.string.ingredients));


            List<String> sandwichAliasList = new ArrayList<>();
            for (int i = 0; i < sandwichAliasArray.length(); i++) {
                sandwichAliasList.add(sandwichAliasArray.getString(i));
            }

            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }

            String placeOfOrigin = sandwichJSONResponse.getString(res.getString(R.string.placeOfOrigin));
            String description = sandwichJSONResponse.getString(res.getString(R.string.description));
            String image = sandwichJSONResponse.getString(res.getString(R.string.image));

            sandwich = new Sandwich(mainName, sandwichAliasList, placeOfOrigin, description, image, ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
