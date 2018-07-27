package com.dadahasa.baking_app.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dadahasa.baking_app.model.Ingredient;

import java.util.List;

public class IngredientsAdapter extends BaseAdapter {

    private Context mContext;
    private List<Ingredient> mIngredients;

    //constructor
    public IngredientsAdapter(Context context, List<Ingredient> ingredients){
        mContext = context;
        mIngredients = ingredients;
    }

    //Returns the number of items the adapter will display
    @Override
    public int getCount() {
        if (mIngredients == null){
            return 0;
        }
        return mIngredients.size();
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
            //define here any layout parameters as needed

        }else {
            textView = (TextView) convertView;
        }
        float quantityFloat = mIngredients.get(position).getQuantity();
        String quantity = Float.toString(quantityFloat);
        String measure = mIngredients.get(position).getMeasure();
        String ingredient = mIngredients.get(position).getIngredient();

        String ingredientText = String.join("\t\t", quantity, measure, ingredient);
        textView.setText(ingredientText);

        return textView;
    }
}
