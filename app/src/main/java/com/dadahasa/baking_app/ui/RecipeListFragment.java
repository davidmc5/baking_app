package com.dadahasa.baking_app.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dadahasa.baking_app.R;

import java.util.Arrays;
import java.util.List;

public class RecipeListFragment extends Fragment {

    //for test only
    List<Integer> recipeIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

    //mandatory empty constructor
    public RecipeListFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate the listView layout to display all the recipe names
        final View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        //get a reference to the listView
        ListView listView = rootView.findViewById(R.id.recipe_names_view);

        //create the adapter and bind the data
        //NOTE: USING DUMMY DATA (recipeIds) TO TEST!
        RecipeListAdapter mAdapter = new RecipeListAdapter(getContext(), recipeIds);

        // set the adapter on to the ListView
        listView.setAdapter(mAdapter);

        //return the root view to be placed in the list view of the fragment element
        return rootView;
    }
}
