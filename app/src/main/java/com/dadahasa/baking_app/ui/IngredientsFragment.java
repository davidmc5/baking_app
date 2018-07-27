package com.dadahasa.baking_app.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class IngredientsFragment extends Fragment {

    IngredientsAdapter mAdapter;

    private List<Ingredient> ingredients;

    //private static final String TAG = RecipesActivity.class.getSimpleName();

    //mandatory empty constructor
    public IngredientsFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate the listView layout to display all ingredients
        final View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);

        //get a reference to the listView layout (fragment_ingredients.xml)
        ListView listView = rootView.findViewById(R.id.ingredients);

        //get the bundle with the ingredients list
        if (getArguments()!= null){
            String ingredientsStr = getArguments().getString("ingredients");
            //de-serialize recipe object
            Gson gson = new Gson();
           ingredients = gson.fromJson(ingredientsStr, new TypeToken<List<Ingredient>>(){}.getType());
        }
        //create the adapter and bind the data
        if (mAdapter == null) {
            mAdapter = new IngredientsAdapter(getContext(), ingredients);
            // attach the adapter to ListView
            listView.setAdapter(mAdapter);
        }
        //return the FRAGMENT view to be placed in the list view of the fragment element
        return rootView;

    }
}
