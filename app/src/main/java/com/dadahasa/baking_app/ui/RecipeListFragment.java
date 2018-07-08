package com.dadahasa.baking_app.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.RecipeApi;
import com.dadahasa.baking_app.model.Recipe;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeListFragment extends Fragment {

    //for test only
    //List<Integer> recipeIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

    RecipeListAdapter mAdapter;
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://go.udacity.com/";
    private List<Recipe> recipeList;

    private static final String TAG = RecipeActivity.class.getSimpleName();

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
        if (mAdapter == null) {
            mAdapter = new RecipeListAdapter(getContext(), recipeList);
            // attach the adapter to ListView
            listView.setAdapter(mAdapter);
            //retrieve data and bind it to the adapter
            getRecipesData();
        }

        //return the FRAGMENT view to be placed in the list view of the fragment element
        return rootView;
        //return listView;
    }



    public void getRecipesData(){

        //create an instance of the API using retrofit
        // this instance will handle the REST/JSON requests to the URL
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        //call the method defined on the RecipeApi interface to get the recipes
        RecipeApi recipeApi = retrofit.create(RecipeApi.class);
        Call<List<Recipe>> call;

        //Get the recipes into an array of recipe objects according to our data models
        call = recipeApi.getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                //if (response.isSuccesful() ){
                //here we get our recipe list
                recipeList = response.body();
                Log.d(TAG, "\n\n NUMBER OF RECIPES RECEIVED: " + recipeList.size() + "\n\n");

                //update the adapter
                mAdapter.addData(recipeList);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d(TAG, "\n\n FAILURE TO GET RECIPES: " + t.getMessage() + "\n\n");
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
