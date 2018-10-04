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
import com.dadahasa.baking_app.model.Step;
import com.google.gson.Gson;

import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class StepsFragment extends Fragment {


    Recipe recipe;

    StepsAdapter mAdapter = null;
    private RecyclerView mRecyclerView = null;

    static final int NEXT_REQUEST = 1; //next/previous request code from the detail step

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
        mAdapter = new StepsAdapter( getContext(), recipe, new StepsAdapter.StepClickListener() {

            //This is an interface method of the StepsAdapter class to be notified of the position clicked
            @Override
            public void onStepClick(int clickedStepIndex) {
                //for testing:
                //Toast.makeText(getContext(), "Clicked " + clickedStepIndex, Toast.LENGTH_LONG).show();

                if(clickedStepIndex == 0){
                    //get ingredients list
                    List<Ingredient> ingredients = recipe.getIngredients();

                    //Serialise Ingredients list
                    Gson gson = new Gson();
                    String ingredientsStr = gson.toJson(ingredients);

                    final Intent intent = new Intent(getContext(), IngredientsActivity.class);
                    intent.putExtra("ingredients", ingredientsStr);

                    startActivity(intent);
                }else{
                    //collect clicked step data to pass to the intent
                    List<Step> steps = recipe.getSteps();
                    //index 0 is reserved to list the ingredients. Recipe steps start at 1 on the adapter
                    // Use index-1 to get the correct step from the List<steps> starting with zero for the first step.
                    Step step = steps.get(clickedStepIndex-1);

                    //serialize the step object element from List<Steps>
                    Gson gson = new Gson();
                    String stepJson = gson.toJson(step);

                    final Intent intent = new Intent(getContext(), StepDetailActivity.class);
                    intent.putExtra("stepJson", stepJson);
                    //we'll use the step index to navigate to previous or next from any detail step.
                    intent.putExtra("stepIndex",clickedStepIndex-1);
                    //Toast.makeText(getActivity(), "Step Index is " + String.valueOf(clickedStepIndex), Toast.LENGTH_SHORT).show();
                    startActivityForResult(intent, NEXT_REQUEST);
                }
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        //return the FRAGMENT view to be placed in the list view of the fragment element
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        //Check which request we're responding to and that it was successful
        if (requestCode == NEXT_REQUEST && resultCode == RESULT_OK) {
            //the user selected to navigate to previous or next step
            //Check the returned Intent data to see which one
            if (resultData.hasExtra("MESSAGE")) {
                int nextStep = resultData.getExtras().getInt("MESSAGE", 1);
                if (nextStep <0){
                    nextStep = 0;
                }

                //call detail step intent with the json data
                //Toast.makeText(getActivity(), "Returned: " + String.valueOf(nextStep), Toast.LENGTH_SHORT).show();
                //collect clicked step data to pass to the intent
                List<Step> steps = recipe.getSteps();
                Step step = steps.get(nextStep);

                //serialize the step object element from List<Steps>
                Gson gson = new Gson();
                String stepJson = gson.toJson(step);

                final Intent intent = new Intent(getContext(), StepDetailActivity.class);
                intent.putExtra("stepJson", stepJson);
                //we'll use the step index to navigate to previous or next from any detail step.
                intent.putExtra("stepIndex", nextStep);
                //Toast.makeText(getActivity(), "Step Index is " + String.valueOf(clickedStepIndex), Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, NEXT_REQUEST);
            }
        } else {
            if (resultCode == RESULT_CANCELED) {
                // Return to the steps list
            }
        }
    }


    //this is a call back for the host activity to pass the Recipe object to this fragment
    public void setRecipe(Recipe recipeClicked){
        recipe = recipeClicked;
    }
}
