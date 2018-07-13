package com.dadahasa.baking_app.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dadahasa.baking_app.R;

public class StepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        //Retrieve the data from the intent (extras)
        String recipeName = getIntent().getStringExtra("recipeName");
        setTitle(recipeName);
    }
}
