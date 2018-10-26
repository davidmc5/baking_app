package com.dadahasa.baking_app;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class ListWidgetService extends RemoteViewsService {

    String ingredients;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }

    class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

        Context mContext;

        public ListRemoteViewsFactory(Context applicationContext){
            mContext = applicationContext;
        }

        @Override
        public void onCreate() {
        }


        //called on start and when AppWidgetManager.notifyAppWidgetViewDataChanged is called
        @Override
        public void onDataSetChanged() {
            //TODO -- get the recipe
            ingredients = BakingWidgetProvider.getIngredients();
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            //if (ingredients == null) return 0;
            return 3;
        }

        @Override
        public RemoteViews getViewAt(int i) {
            //if (ingredients == null) return null;

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
            //views.setTextViewText(R.id.widget_list_view, ingredients);
            views.setTextViewText(R.id.widget_list_item, "KAKA");

            //TODO  // Fill in the onClick PendingIntent
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }



}
