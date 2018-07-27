package com.dadahasa.baking_app.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dadahasa.baking_app.R;

public class IngredientsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        //Retrieve the list of ingredients from the intent (extras)
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String ingredientsStr = extras.getString("ingredients");

            //put the ingredientsStr in a bundle to pass to the fragment
            Bundle bundle = new Bundle();
            bundle.putString("ingredients", ingredientsStr);

            //ADD DYNAMIC FRAGMENT (only the first time the app runs)
            if (savedInstanceState == null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                IngredientsFragment ingredientsFragment = new IngredientsFragment();

                //pass the bundle with the json ingredients list object to the fragment
                ingredientsFragment.setArguments(bundle);
                ft.add(R.id.ingredients_fragment, ingredientsFragment);
                ft.commit();

                //force adding the new fragment before capturing a reference and send the recipe object
                getSupportFragmentManager().executePendingTransactions();
            }
        }
    }
}

