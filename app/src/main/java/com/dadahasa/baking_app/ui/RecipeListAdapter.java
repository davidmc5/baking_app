package com.dadahasa.baking_app.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RecipeListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mRecipeIds;


    //constructor
    public RecipeListAdapter(Context context, List<Integer> recipeIds){
        mContext = context;
        mRecipeIds = recipeIds;
    }

    //Returns the number of items the adapter will display
    @Override
    public int getCount() {
        return mRecipeIds.size();
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

        TextView textView;

        if (convertView == null) {
            //create a new textview to hold the text if there are no recycled ones
            textView = new TextView(mContext);

            //define here any layout parameters
        }else {
            textView = (TextView) convertView;
        }
        textView.setText(mRecipeIds.get(position).toString());
        return textView;
    }
}
