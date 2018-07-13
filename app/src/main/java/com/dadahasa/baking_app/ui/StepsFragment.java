package com.dadahasa.baking_app.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.RecipeApi;
import com.dadahasa.baking_app.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StepsFragment extends Fragment {


    //mandatory empty constructor
    public StepsFragment(){
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate the listView layout to display all the recipe names
        final View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        //get a reference to the listView
       // ListView listView = rootView.findViewById(R.id.recipe_names_view);


        //return the FRAGMENT view to be placed in the list view of the fragment element
        return rootView;

    }





}
