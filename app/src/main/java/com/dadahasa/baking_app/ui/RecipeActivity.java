package com.dadahasa.baking_app.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dadahasa.baking_app.R;

public class RecipeActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
    }

    @Override
    public void onRecipeSelected(int position, String recipeName) {
        //start steps activity
        final Intent intent = new Intent(this, StepsActivity.class);
        //Pass data to steps activity via extras
        intent.putExtra("recipeName", recipeName);
        //Start intent
        startActivity(intent);
    }
}
