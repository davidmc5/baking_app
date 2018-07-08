package com.dadahasa.baking_app.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class RecipeActivity extends AppCompatActivity {
//implements MainAdapter.MovieClickListener {

    //declare the variables
    //private static Retrofit retrofit = null;
    //public static final String BASE_URL = "http://go.udacity.com/";
    //private List<Recipe> recipeList;

    //private static final String TAG = RecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

          //retrieve recipes
        //getRecipesData();

    }


/*
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
                //addData(recipeList);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d(TAG, "\n\n FAILURE TO GET RECIPES: " + t.getMessage() + "\n\n");
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
*/

}
