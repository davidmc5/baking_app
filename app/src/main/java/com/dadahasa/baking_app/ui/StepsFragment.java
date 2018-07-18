package com.dadahasa.baking_app.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Recipe;
import com.dadahasa.baking_app.model.Step;

import java.util.List;

public class StepsFragment extends Fragment {


    //mandatory empty constructor
    public StepsFragment(){
    }

    Recipe recipe;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        //inflate the listView layout to display all the recipe names
        final View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        //get a reference to the fragment's textView
        textView = rootView.findViewById(R.id.recipe_steps);

        //display the name of the recipe
        textView.setText("ta tia la sorda");


        //return the FRAGMENT view to be placed in the list view of the fragment element
        return rootView;

    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //this makes sure the host activity has implemented the callback interface
    }

    public void setRecipe(Recipe recipeClicked){
        recipe = recipeClicked;

        //For Testing: Show the second step of the recipe
        List<Step> steps = recipe.getSteps();
        textView.setText(steps.get(1).getDescription());

        //For Testing: Print a toast with the recipe name
        String recipeName = recipe.getName();
        Toast.makeText(getContext(), "Sent Recipe " + recipeName, Toast.LENGTH_LONG).show();
        //textView.setText("It's a beautiful World");
    }



}
