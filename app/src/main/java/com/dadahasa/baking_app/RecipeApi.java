package com.dadahasa.baking_app;

import com.dadahasa.baking_app.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApi {

    //To retrieve recipes from the link, use this on the onCreate's host Activity:
    //call = recipe.getRecipes();
    //call.enqueue(new Callback<RecipeDelete>() {...

    @GET("android-baking-app-json")
    Call<List<Recipe>> getRecipes();
}