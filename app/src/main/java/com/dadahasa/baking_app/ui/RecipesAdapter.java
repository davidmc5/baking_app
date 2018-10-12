package com.dadahasa.baking_app.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Recipe;

import java.util.List;

public class RecipesAdapter extends BaseAdapter {

    private Context mContext;
    private List<Recipe> mRecipeList;


    //constructor
    public RecipesAdapter(Context context, List<Recipe> recipeList){
        mContext = context;
        mRecipeList = recipeList;
    }

    //Returns the number of items the adapter will display
    @Override
    public int getCount() {
        if (mRecipeList == null){
            return 0;
        }
        return mRecipeList.size();
    }

    //the following two methods are not used
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }


    //Creates a new TextView for each item referenced by the adapter
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //create a textView for the adapter to display each item in the list
        TextView textView;
        if (convertView == null) {
            //create a new textview to hold the text if there are no recycled ones
            textView = new TextView(mContext);

            //define here any layout parameters
            //surround each recipe with a grey background to simulate a card
            textView.setTextSize(30);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey));

        }else {
            textView = (TextView) convertView;
        }
        textView.setText(mRecipeList.get(position).getName());
        return textView;
    }

    //method to pass the data received to the adapter
    // this is called by the retrofit method onResponse when new API data is fetched
    public void addData(List<Recipe> recipeList) {
        this.mRecipeList = recipeList;
        notifyDataSetChanged();
    }

}
