package com.dadahasa.baking_app.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Recipe;
import com.google.gson.Gson;


public class StepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        //Retrieve the data from the intent (extras)
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String recipeStr = extras.getString("recipe");

            //de-serialize recipe object
            Gson gson = new Gson();
            Recipe recipe = gson.fromJson(recipeStr, Recipe.class);

            //get the recipe name from the Recipe object
            String recipeName = recipe.getName();
            setTitle(recipeName);

            //ADD DYNAMIC FRAGMENT (only the first time the app runs)
            if (savedInstanceState == null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.steps_fragment, new StepsFragment());
                ft.commit();
                //force adding the new fragment before capturing a reference and send the recipe object
                getSupportFragmentManager().executePendingTransactions();
            }

            //pass recipe object to fragment
            //Lookup the fragment instance to call one of its methods
            StepsFragment fragment = (StepsFragment) getSupportFragmentManager().findFragmentById(R.id.steps_fragment);

            //Call the fragment method to pass the recipe object
            fragment.setRecipe(recipe);
        }
    }
}
