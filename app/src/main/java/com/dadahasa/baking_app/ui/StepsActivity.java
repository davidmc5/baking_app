package com.dadahasa.baking_app.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Recipe;
import com.google.gson.Gson;


public class StepsActivity extends AppCompatActivity {

    public boolean mTwoPane;
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

            if (findViewById(R.id.recipe_steps_tablet) != null){
                //Toast.makeText(this, "TWO PANE!", Toast.LENGTH_SHORT).show();
                mTwoPane = true;
            }else{
                mTwoPane = false;
            }

            //ADD DYNAMIC FRAGMENTS (only the first time the app runs)
            if (savedInstanceState == null) {

                //Add Steps Fragment
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.steps_fragment, new StepsFragment());

                if (mTwoPane) {
                    //will use the tablet layout.
                    // Add Detail Steps Fragment on the right pane
                    StepDetailFragment stepDetailFragment = new StepDetailFragment();
                    ft.add(R.id.step_detail_fragment_container, stepDetailFragment);
                    //hide fragment since there is no detail steps to show (until there is a click)
                    ft.hide(stepDetailFragment);
                }
                ft.commit();

                //force adding the new fragment before capturing a reference and send the recipe object
                getSupportFragmentManager().executePendingTransactions();
            }


            //pass recipe object to fragment
            //Lookup the fragment instance to call one of its methods
            StepsFragment fragment = (StepsFragment) getSupportFragmentManager().findFragmentById(R.id.steps_fragment);

            //Call the StepsFragment method (setRecipe) to pass the recipe object and the two-pane flag
            fragment.setRecipe(recipe, mTwoPane);
        }
    }
}
