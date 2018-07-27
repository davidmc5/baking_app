package com.dadahasa.baking_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Ingredient;
import com.dadahasa.baking_app.model.Recipe;
import com.google.gson.Gson;

import java.util.List;

public class StepsFragment extends Fragment {


    Recipe recipe;

    StepsAdapter mAdapter = null;
    private RecyclerView mRecyclerView = null;

    //Data For testing only
    //private List<String> testData = new ArrayList<>(Arrays.asList("Step 1", "Step 2", "Step 3", "Step 4", "Step 5"));

    //mandatory empty constructor
    public StepsFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate the layout
        final View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        //set recyclerView
        mRecyclerView = rootView.findViewById(R.id.recipe_steps);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);



        String recipeName = recipe.getName();
        Toast.makeText(getContext(), "Recipe " + recipeName, Toast.LENGTH_LONG).show();





        //set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        //set adapter
        mAdapter = new StepsAdapter(getContext(), recipe, new StepsAdapter.StepClickListener() {

            //This is an interface method of the StepsAdapter class to be notified of the position clicked
            @Override
            public void onStepClick(int clickedStepIndex) {

                Toast.makeText(getContext(), "Clicked " + clickedStepIndex, Toast.LENGTH_LONG).show();

                if(clickedStepIndex == 0){
                    //get ingredients list
                    List<Ingredient> ingredients = recipe.getIngredients();

                    //Serialise Ingredients list
                    Gson gson = new Gson();
                    String ingredientsStr = gson.toJson(ingredients);

                    final Intent intent = new Intent(getContext(), IngredientsActivity.class);
                    intent.putExtra("ingredients", ingredientsStr);

                    startActivity(intent);
                }
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        //return the FRAGMENT view to be placed in the list view of the fragment element
        return rootView;
    }

    //this is a call back for the host activity to pass the Recipe object to this fragment
    public void setRecipe(Recipe recipeClicked){
        recipe = recipeClicked;
    }
}
