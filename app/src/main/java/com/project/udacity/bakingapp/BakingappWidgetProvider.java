package com.project.udacity.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingappWidgetProvider extends AppWidgetProvider {

    static ArrayList<String> widgetText = null ;
    static String widgetTextString = null ;
    static String recepename = null ;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        if (widgetText == null){
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bakingapp_widget_provider);
            views.setTextViewText(R.id.appwidget_text, "Please choose recepe from app first");
            views.setViewVisibility(R.id.widgettitle , View.GONE);
        }else {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bakingapp_widget_provider);
            views.setTextViewText(R.id.appwidget_text, widgetTextString);
            views.setTextViewText(R.id.widgettitle , recepename);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
       /* Intent intent = new Intent(context , MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context , 0 , intent ,0);
        views.setOnClickPendingIntent(R.id.appwidget_text , pendingIntent);
        */

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        widgetTextString = null ;
        widgetText = intent.getStringArrayListExtra("Ingrediences");
        recepename = intent.getStringExtra("recepeName");
        // Instruct the widget manager to update the widget
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bakingapp_widget_provider);
        if (widgetText != null) {
            for (int i = 0; i < widgetText.size(); i++) {
                widgetTextString = widgetTextString + widgetText.get(i) + "\n";
            }
        }
        views.setTextViewText(R.id.widgettitle , recepename);
        views.setTextViewText(R.id.appwidget_text, widgetTextString);
        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, BakingappWidgetProvider.class),views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bakingapp_widget_provider);
            views.setTextViewText(R.id.appwidget_text, widgetTextString);
            views.setTextViewText(R.id.widgettitle , recepename);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }



    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bakingapp_widget_provider);
        views.setTextViewText(R.id.appwidget_text, widgetTextString);
        views.setTextViewText(R.id.widgettitle , recepename);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onEnabled(Context context) {
        if (widgetText == null){
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bakingapp_widget_provider);
            views.setTextViewText(R.id.appwidget_text, "Please choose recepe from app first");
            views.setViewVisibility(R.id.widgettitle , View.GONE);
        }else {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bakingapp_widget_provider);
            views.setTextViewText(R.id.appwidget_text, widgetTextString);
            views.setTextViewText(R.id.widgettitle , recepename);
            // Instruct the widget manager to update the widget
            AppWidgetManager.getInstance(context).updateAppWidget(
                    new ComponentName(context, BakingappWidgetProvider.class),views);        }    }


    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

