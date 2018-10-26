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

    static String recipeIngredients;



    //method for ListWidgetService.onDataSetChanged() to retrieve the list of ingredients
    static String getIngredients(){
        return recipeIngredients;
    }


    //This is called when the app needs to update the widget (on start and predefined interval)
    // It only runs the first time the app starts and every 30 minutes afterwards
    //It is called by WidgetIntentService.onHandleIntent

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        updateRecipeWidgets(context, appWidgetManager, appWidgetIds, recipeIngredients);
    }

    //This is called by onUpdate() with all the widget IDs
    public static void updateRecipeWidgets(
            Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, String ingredients) {
        //Toast.makeText(context, "Another Ingredient: " + ingredients, Toast.LENGTH_LONG).show();

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, ingredients);
        }
    }

    //This is called by updateRecipeWidgets() to update just one widget ID
    //This is a static method so we can call it with the update method from anywhere.
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String ingredients) {

        //TODO - Need to get the recipe instead so we can get the recipe name to display
        recipeIngredients = ingredients;


        //get initial test to display, "Baking App"
        //Need to get the recipe name instead
        CharSequence widgetText = context.getString(R.string.app_name);

        // Create an Intent to launch MainActivity when clicked
        Intent intent = new Intent(context, RecipesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


        // Construct the RemoteViews object
        //this works but only with a textView.
        //I need to pass the ingredient list to the ListRemoteViewsFactory adapter
        //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget_provider);
        RemoteViews views = getListRemoteView(context);

        //TODO - set a listview to display the ingredients
        //Set the text to display on the text view (not the ListView)

        //if no recipe clicked, display the app name on the widget. Otherwise, display the recipe name
        // it should be the recipe name
        if (ingredients == null) {
            views.setTextViewText(R.id.empty_view, widgetText);
        }else{
            //views.setTextViewText(R.id.appwidget_empty_view_text, "Ingredients");
            //views.setTextViewText(R.id.widget_list_view, "Ingredients");
        }

        //TODO - This onClick is not working with the FrameLayout, only with the textview
        // Widgets click handlers only allow to launch pending intents
        //attach click handler to the text to launch the recipe app when clicked
        //views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
        views.setOnClickPendingIntent(R.id.empty_view, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }




    //Creates and returns the RemoteViews to be displayed in the ListView mode widget
    private static RemoteViews getListRemoteView(Context context) {

        //Create a RemoteView object with the ListView layout
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget_provider);


        //Connect the remoteViews object (views) to the remoteViewsService (ListWidgetService)
        //to bind the remoteViews with the adapter's data
        //To do that we'll create an intent pointing to the ListWidgetService
        //and then call the setRemoteAdapter to link the ListView with the intent


        // Set the ListWidgetService intent to act as the adapter for the ListView
        Intent intent = new Intent(context, ListWidgetService.class);

        //bind the ListView remoteViews with the adapter's data
        views.setRemoteAdapter(R.id.widget_list_view, intent);

        // The empty view is displayed when the collection has no items. It should be a sibling
        // of the collection view.
        views.setEmptyView(R.id.widget_list_view, R.id.empty_view);

        // Set the PlantDetailActivity intent to launch when clicked
        //Intent appIntent = new Intent(context, PlantDetailActivity.class);
        //PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //views.setPendingIntentTemplate(R.id.widget_grid_view, appPendingIntent);




        return views;
    }




    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // Perform any action when one or more AppWidget instances have been deleted
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

