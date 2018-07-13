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

public class RecipeListFragment extends Fragment {


    RecipeListAdapter mAdapter;
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://go.udacity.com/";
    private List<Recipe> recipeList;

    private static final String TAG = RecipeActivity.class.getSimpleName();

    //mandatory empty constructor
    public RecipeListFragment(){
    }

    //callback to pass the recipe index clicked to the host activity
    OnRecipeClickListener mCallback;

    //this interface calls a method in the host activity, onRecipeSelected,
    //to pass the recipe index clicked
    public interface OnRecipeClickListener {
        void onRecipeSelected(int position, String recipeName);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //this makes sure the host activity has implemented the callback interface
        try {
            mCallback = (OnRecipeClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement onRecipeClickListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate the listView layout to display all the recipe names
        final View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        //get a reference to the listView and attach the click listener
        ListView listView = rootView.findViewById(R.id.recipe_names_view);
        listView.setOnItemClickListener(new OnRecipeClicked());

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

    }


    class OnRecipeClicked implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
           onRecipeClicked(parent, view, position, id);
        }
    }

    private void onRecipeClicked(AdapterView<?> parent, View view, int position, long id){
        //put here what we want to happen when a recipe is clicked
        //This calls the method onRecipeSelected on the host activity

        //retrieve the recipe name
        Recipe recipe = recipeList.get(position);
        String recipeName = recipe.getName();
        //send the data to the host activity via the interface callback
        mCallback.onRecipeSelected(position, recipeName);
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
                Toast.makeText(getContext(), "Network Unavailable", Toast.LENGTH_LONG).show();
            }
        });
    }

}
