package com.dadahasa.baking_app.ui;

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
    public void onRecipeSelected(int position) {
        Toast.makeText(this, "Recipe Clicked " + position, Toast.LENGTH_LONG).show();
    }
}
