package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String TAG = JsonUtils.class.getSimpleName();

//    json keys properties
    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
//        Log.e(TAG, "parseSandwichJson: json=" + json);

//        returning Sandwich object
        Sandwich sandwich = new Sandwich();

        try {
//        Creating json object from json string
            JSONObject reader = new JSONObject(json);
//            Getting name object
            JSONObject name = reader.getJSONObject(NAME);

//            Getting mainName property from reader
            String mainName = name.getString(MAIN_NAME);
//            set mainName in sandwich object
            sandwich.setMainName(mainName);

//            Getting alsoKnownAs property
            JSONArray alsoKnownAs = name.getJSONArray(ALSO_KNOWN_AS);
            List<String> listAlsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                listAlsoKnownAs.add(alsoKnownAs.getString(i));
            }
//            set alsoKnownAs on sandwich object
            sandwich.setAlsoKnownAs(listAlsoKnownAs);

//            Getting placeOfOrigin from reader
            String placeOfOrigin = reader.getString(PLACE_OF_ORIGIN);
//            set placeOfOrigin on sandwich object
            sandwich.setPlaceOfOrigin(placeOfOrigin);

//            Getting description property from reader
            String description = reader.getString(DESCRIPTION);
//            Set description on sandwich object
            sandwich.setDescription(description);

//            Getting image property from reader
            String image = reader.getString(IMAGE);
//            set image on sandwich object
            sandwich.setImage(image);

//            Getting ingredients property from reader
            JSONArray ingredients = reader.getJSONArray(INGREDIENTS);
            List<String> listIngredients = new ArrayList<>();
            for (int i = 0; i < ingredients.length(); i++) {
                listIngredients.add(ingredients.getString(i));
            }
//            set ngredients on sandwich object
            sandwich.setIngredients(listIngredients);

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();

            return null;
        }

    }
}
