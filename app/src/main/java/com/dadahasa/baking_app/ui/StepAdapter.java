package com.dadahasa.baking_app.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dadahasa.baking_app.R;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepsViewHolder> {

    private Context context;
    private TextView mTextView;


    //test data to display on the list
    private List<String> testData;

    //reference for the listener interface
    private StepClickListener stepsListener;

    public interface StepClickListener {
        void onStepClick(int clickedStepIndex);
    }

    //Constructor to pass the data object with list of steps from the recipe model
    public StepAdapter(Context context, List<String> testData, StepClickListener stepsListener){
        this.context = context;
        this.testData = testData;
        this.stepsListener = stepsListener;
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
                //this calls the onStepClick method of the activity (step fragment)
                //and passes the defined parameters (the position index in this case)
                stepsListener.onStepClick(stepIndex);
            }
        });
        return mStepsViewHolder;
    }

    @Override
    public void onBindViewHolder (StepsViewHolder holder, int position){
        String text = testData.get(position);
        mTextView.setText(text);
    }

    @Override
    public int getItemCount(){
        if (testData == null){
            return 0;
        }
        return testData.size();
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
