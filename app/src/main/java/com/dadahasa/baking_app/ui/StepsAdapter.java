package com.dadahasa.baking_app.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Recipe;
import com.dadahasa.baking_app.model.Step;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private Context context;
    private TextView mTextView;
    private Recipe recipe;
    private List<Step> steps;



    //test data to display on the list
    //private List<String> testData;

    //reference for the listener interface
    private StepClickListener stepsListener;

    public interface StepClickListener {
        void onStepClick(int clickedStepIndex);
    }


//** change List<String> to Recipe
    //Constructor to pass the data object with list of steps from the recipe model
    //public StepsAdapter(Context context, List<String> testData, StepClickListener stepsListener){
    public StepsAdapter(Context context, Recipe recipe, StepClickListener stepsListener){

    this.context = context;
        //this.testData = testData;
        this.recipe = recipe;
        this.stepsListener = stepsListener;
        this.steps = recipe.getSteps();
    }


    //Adapters must implement the following methods

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        context = parent.getContext();
        int layoutId = R.layout.steps_view_rv;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutId, parent, shouldAttachToParentImmediately);

        final StepsViewHolder mStepsViewHolder = new StepsViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stepIndex = mStepsViewHolder.getAdapterPosition();
                //this calls the onStepClick method of the activity (steps fragment)
                //and passes the defined parameters (the position index in this case)
                stepsListener.onStepClick(stepIndex);
            }
        });
        return mStepsViewHolder;
    }

    @Override
    public void onBindViewHolder (StepsViewHolder holder, int position){

        String text;

        //Display the text "Ingredients" in the first position
        if ( position == 0){
            mTextView.setText(context.getResources().getString(R.string.ingredients));
        }else {
            String stepName = steps.get(position-1).getShortDescription();
            if (position == 1){
                //do not use the labels for index zero for the first step -- description)
                text = stepName;

            }else {
                text = position - 1 + " - " + stepName;
            }
            mTextView.setText(text);
        }
    }

    @Override
    public int getItemCount(){
        if (steps == null){
            return 0;
        }
        return steps.size()+1;
    }


    //this method is necessary to avoid the recycler view change view while scrolling
    @Override
    public int getItemViewType(int position) {
        return position;
    }


    //Add the view holder class
    // A View holder inflates the cell layout for each view (a single step) when needed
    // A view holder stores and recycles views as they are scrolled off screen
    // creating new views or reusing hidden ones.
    public class StepsViewHolder extends RecyclerView.ViewHolder {

        public StepsViewHolder(View itemView){
            super(itemView);
            mTextView = itemView.findViewById(R.id.step_view);
        }
    }
}
