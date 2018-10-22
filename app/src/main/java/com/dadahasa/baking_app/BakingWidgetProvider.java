package com.dadahasa.baking_app;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.dadahasa.baking_app.model.Ingredient;
import com.dadahasa.baking_app.model.Recipe;
import com.dadahasa.baking_app.ui.RecipesActivity;
import com.dadahasa.baking_app.ui.WidgetIntentService;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String ingredients) {

        //This is a static method so we can call it with the update method from anywhere.

        //get initial test to display
        CharSequence widgetText = context.getString(R.string.app_name);


        // Create an Intent to launch MainActivity when clicked
        Intent intent = new Intent(context, RecipesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget_provider);

        //Retrieve the Extras with the Ingredients
        //TODO - set a listview to display the ingredients
        //Set the text to display
        views.setTextViewText(R.id.appwidget_text, ingredients);



        // Widgets click handlers only allow to launch pending intents
        //attach click handler to the text to launch the recipe app when clicked
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        updateRecipeWidgets(context, appWidgetManager, appWidgetIds, "HELLO");
    }

    //////////////////////////////////////
    //this only runs the first time the app starts and every 30 minutes afterwards
    public static void updateRecipeWidgets(
            Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, String ingredients) {
        Toast.makeText(context, "Another Ingredient: " + ingredients, Toast.LENGTH_LONG).show();

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, ingredients);
        }
    }




        @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

