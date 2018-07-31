package com.dadahasa.baking_app.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dadahasa.baking_app.R;

public class StepDetailActivity extends AppCompatActivity {

    private static final String TAG_STEP_DETAIL_FRAGMENT = "TAG_STEP_DETAIL_FRAGMENT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        //retrieve the step data
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String stepJson = extras.getString("stepJson");

            //send step string data to the step fragment
            Bundle bundle = new Bundle();
            bundle.putString("stepJson", stepJson);

            //insert Step Detail fragment into container
            StepDetailFragment stepDetailFragment = new StepDetailFragment();

            //add the extras
            stepDetailFragment.setArguments(bundle);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.step_detail_fragment_container, stepDetailFragment, TAG_STEP_DETAIL_FRAGMENT);
            ft.commit();

            //force adding the new fragment before capturing a reference and send the recipe object
            getSupportFragmentManager().executePendingTransactions();
        }
    }

}
