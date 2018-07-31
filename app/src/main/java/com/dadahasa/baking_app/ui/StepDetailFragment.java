package com.dadahasa.baking_app.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Step;
import com.google.gson.Gson;

public class StepDetailFragment extends Fragment {

    //mandatory empty constructor
    public StepDetailFragment(){
    }

    String description;
    String thumbnailURL;
    String videoURL;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);

        //Retrieve the step data from the bundle
        if (getArguments() != null){
            String stepJson = getArguments().getString("stepJson");
            //de-serialize step object
            Gson gson = new Gson();
            Step step = gson.fromJson(stepJson, Step.class);

            description = step.getDescription();
            thumbnailURL = step.getThumbnailURL();
            videoURL = step.getVideoURL();
        }

        TextView textView = view.findViewById(R.id.step_detail_view);
        textView.setText(description + "\n" + thumbnailURL + "\n" + videoURL);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO
            }
        });

        //add more views/widgets here

       return view;
    }
}

