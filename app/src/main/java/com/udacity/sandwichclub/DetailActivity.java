package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView akaTextView;
    private TextView ingredientsTextView;
    private TextView placeOfOriginTextView;
    private TextView descriptionTextView;

    private LinearLayout ingredientsLayout;
    private LinearLayout akaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        populateAliasTextView(sandwich);
        populateIngredientsTextView(sandwich);
        populatePlaceOfOriginTextView(sandwich);
        populateDescriptionTextView(sandwich);
    }

    private void populateAliasTextView(Sandwich sandwich) {
        if (!sandwich.getAlsoKnownAs().isEmpty()) {
            akaTextView = findViewById(R.id.also_known_tv);
            akaTextView.setText(delimitedText(sandwich.getAlsoKnownAs()));
        } else {
            akaLayout = findViewById(R.id.aka_container);
            akaLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void populateIngredientsTextView(Sandwich sandwich) {
        if (!sandwich.getIngredients().isEmpty()) {
            ingredientsTextView = findViewById(R.id.ingredients_tv);
            ingredientsTextView.setText(delimitedText(sandwich.getIngredients()));
        } else {
            ingredientsLayout = findViewById(R.id.ingredients_container);
            ingredientsLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void populatePlaceOfOriginTextView(Sandwich sandwich) {
        placeOfOriginTextView = findViewById(R.id.origin_tv);
        placeOfOriginTextView.setText(sandwich.getPlaceOfOrigin());
    }

    private void populateDescriptionTextView(Sandwich sandwich) {
        descriptionTextView = findViewById(R.id.description_tv);
        descriptionTextView.setText(sandwich.getDescription());
    }

    private String delimitedText(List<String> items) {
        String delimitedString = "";
        for (String item: items) {
            delimitedString += item + "\n";
        }
        return delimitedString;
    }
}
