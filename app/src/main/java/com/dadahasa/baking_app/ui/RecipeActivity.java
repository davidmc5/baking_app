package com.dadahasa.baking_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Recipe;
import com.google.gson.Gson;

public class RecipeActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
    }

    @Override
    public void onRecipeSelected(Recipe recipe) {
        //start steps activity
        final Intent intent = new Intent(this, StepsActivity.class);

        //Serialise recipe object
        Gson gson = new Gson();
        String recipeStr = gson.toJson(recipe);
        intent.putExtra("recipe", recipeStr);


        //Start intent
        startActivity(intent);
    }
}
