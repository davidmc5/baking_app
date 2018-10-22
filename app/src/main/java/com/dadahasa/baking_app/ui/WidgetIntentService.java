package com.dadahasa.baking_app.ui;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.dadahasa.baking_app.BakingWidgetProvider;
import com.dadahasa.baking_app.model.Ingredient;

import java.util.List;

import static com.dadahasa.baking_app.ui.StepDetailFragment.stepIndex;


/*
* The intent service class is to execute pendingIntents to perform background tasks without launching the activity
 */
public class WidgetIntentService extends IntentService {

    public static final String ACTION_UPDATE_RECIPE_WIDGETS = "com.dadahasa.baking_app.action.update_recipe_widget";

    //default constructor
    public WidgetIntentService() {
        super("WidgetIntentService");
    }


    /**
     * Starts this WidgetIntentService to perform UpdateRecipeWidget with the action ACTION_UPDATE_RECIPE_WIDGETS.
     * If the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateRecipeWidget(Context context, String ingredients) {
        Intent intent = new Intent(context, WidgetIntentService.class);

        if (ingredients == null){
            Toast.makeText(context, "No Ingredients", Toast.LENGTH_SHORT).show();
        }else {
            intent.setAction(ACTION_UPDATE_RECIPE_WIDGETS);
            intent.putExtra("ingredients", ingredients);

            //for testing
            //this is working. When clicking a recipe this is being called by the RecipesFragment
            Toast.makeText(context, "Ingredient" + ingredients, Toast.LENGTH_SHORT).show();
            context.startService(intent);
        }
    }

    //NOTE: FOR THE FOLLOWING METHOD TO WORK, THIS INTENTSERVICE CLASS (WidgetIntentService.java)
    // NEEDS TO BE DECLARED IN THE MANIFEST LIKE THIS:
    //<service android:name=".ui.WidgetIntentService" />
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //gets the data from the incoming intent
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_RECIPE_WIDGETS.equals(action)) {
                String ingredients = intent.getStringExtra("ingredients");
                //Log.d("INGREDIENTS ////-----: ", ingredients);
                handleActionUpdateRecipeWidgets(ingredients);
            }
        }
    }



    /**
     * Handle action UpdateRecipeWidgets in the provided background thread with the provided
     * parameters.
     * This method collects the recipe to display and updates all existent widgets of this app
     * and calls the WidgetProvider passing in an instance of the AppWidgetManager, the Widget IDs and the data to be displayed.
     */
    private void handleActionUpdateRecipeWidgets(String ingredients) {

        //for testing
        //not working
        //Toast.makeText(getBaseContext(), "Ingredient::::: " + ingredients, Toast.LENGTH_SHORT).show();
        //Log.d("INGREDIENTS ||||-----: ", ingredients);


        //get the ids of all widgets from this app
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));

        //update all widgets by calling the update method of the widget provider
        BakingWidgetProvider.updateRecipeWidgets(this, appWidgetManager, appWidgetIds, ingredients);
    }

}