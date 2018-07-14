package com.dadahasa.baking_app.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Recipe;

public class StepsFragment extends Fragment {


    //mandatory empty constructor
    public StepsFragment(){
    }

    Recipe recipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate the listView layout to display all the recipe names
        final View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        //get a reference to the fragment's textView
        TextView textView = rootView.findViewById(R.id.recipe_steps);

        //display the name of the recipe
        textView.setText("ta tia la sorda");


        //return the FRAGMENT view to be placed in the list view of the fragment element
        return rootView;

    }

    public void setRecipe(Recipe recipeClicked){
        recipe = recipeClicked;
    }



}
