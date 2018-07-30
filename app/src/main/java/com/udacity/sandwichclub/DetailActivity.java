package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    public static final String TAG = DetailActivity.class.getSimpleName();

//    Details activity views
    ImageView imageIv;
    TextView mainNameTv, alsoKnownAsTv, originTv, ingredientsTv, descriptionIv;

//    Sandwich to show details
    Sandwich mSandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        Binding view
        imageIv = (ImageView) findViewById(R.id.image_iv);
        mainNameTv = (TextView) findViewById(R.id.main_name_tv);
        alsoKnownAsTv = (TextView) findViewById(R.id.also_known_tv);
        originTv = (TextView) findViewById(R.id.origin_tv);
        ingredientsTv = (TextView) findViewById(R.id.ingredients_tv);
        descriptionIv = (TextView) findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
//        Log.e(TAG, "onCreate:onItemClick: position=" + position);

        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        mSandwich = JsonUtils.parseSandwichJson(json);
        if (mSandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(mSandwich.getImage())
                .into(imageIv);

        setTitle(mSandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

//        display sandwich mainName
//        if there is no mainName value, display 'unknow' message
        if (mSandwich.getMainName() == null || mSandwich.getMainName().equals("")) {
            mainNameTv.setText(getResources().getString(R.string.unknow));
        }
        mainNameTv.setText(mSandwich.getMainName());

//        display sandwich alsoKnownAs
        String alsoKnownAs = "";
        for (int i = 0; i < mSandwich.getAlsoKnownAs().size(); i++) {
            if (i == (mSandwich.getAlsoKnownAs().size()-1)) alsoKnownAs = alsoKnownAs.concat(mSandwich.getAlsoKnownAs().get(i)+".") ;
            else {
                alsoKnownAs = alsoKnownAs.concat(mSandwich.getAlsoKnownAs().get(i)+", ") ;
            }
        }
//        if there is no alsoKnownAs value, display 'unknow' message
        if (mSandwich.getAlsoKnownAs().size() == 0) {
            alsoKnownAs = getResources().getString(R.string.unknow);
        }
        alsoKnownAsTv.setText(alsoKnownAs);

//        display sandwich ingredients
        String ingredients = "";
        for (int i = 0; i < mSandwich.getIngredients().size(); i++) {
            if (i == (mSandwich.getIngredients().size()-1)) ingredients = ingredients.concat(mSandwich.getIngredients().get(i)+".");
            else {
                ingredients = ingredients.concat(mSandwich.getIngredients().get(i)+", ");
            }
        }
//        if there is no ingredients value, display 'unknow' message
        if (mSandwich.getIngredients().size() == 0) {
            ingredients = getResources().getString(R.string.unknow);
        }
        ingredientsTv.setText(ingredients);

//        display sandwich place of origin
//        if there is no origin value, display 'unknow' message
        if (mSandwich.getPlaceOfOrigin() == null || mSandwich.getPlaceOfOrigin().equals("")) {
            originTv.setText(getResources().getString(R.string.unknow));
        }
        originTv.setText(mSandwich.getPlaceOfOrigin());

//        display sandwich description
//        if there is no description value, display 'unknow' message
        if (mSandwich.getDescription() == null || mSandwich.getDescription().equals("")) {
            descriptionIv.setText(getResources().getString(R.string.unknow));
        }
        descriptionIv.setText(mSandwich.getDescription());

    }
}
