package com.project.udacity.bakingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by momenamiin on 2/11/18.
 */

public class BakingListJSON {


    public String getrecepeName (String Json , int receppostion ) {
        String recepeName;

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(Json);
            JSONObject jsonObject = jsonArray.getJSONObject(receppostion);
            recepeName = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
            recepeName = null;
        }
        return recepeName;
    }

    public int getStepsNum (String Json , int receppostion ){
        JSONArray jsonArray = null;
        int  StepsNum ;
        try {
            jsonArray = new JSONArray(Json);
            JSONObject jsonObject = jsonArray.getJSONObject(receppostion);
            JSONArray stepsArray = jsonObject.getJSONArray("steps");
            StepsNum = stepsArray.length();

        } catch (JSONException e) {
            e.printStackTrace();
            StepsNum = 0;
        }
        return StepsNum;
    }
    public String getLongDescription (String Json , int receppostion , int StepPosision){
        JSONArray jsonArray = null;
        String Description ;
        try {
            jsonArray = new JSONArray(Json);
            JSONObject jsonObject = jsonArray.getJSONObject(receppostion);
            JSONArray stepsArray = jsonObject.getJSONArray("steps");
            JSONObject jsonObject1 = stepsArray.getJSONObject(StepPosision);
            Description = jsonObject1.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
            Description = null;
        }
        return Description;
    }

    public String getVideoUrl (String Json , int receppostion , int StepPosision){
        Log.v("Memo" , "Henaa");
        JSONArray jsonArray = null;
        String VideoUrl ;
        try {
            jsonArray = new JSONArray(Json);
            JSONObject jsonObject = jsonArray.getJSONObject(receppostion);
            JSONArray stepsArray = jsonObject.getJSONArray("steps");
            JSONObject jsonObject1 = stepsArray.getJSONObject(StepPosision);
            VideoUrl = jsonObject1.getString("videoURL");

        } catch (JSONException e) {
            e.printStackTrace();
            VideoUrl = null;
        }
        return VideoUrl;
    }

    public ArrayList<String> getIngerdiance (String Json , int postion){
        ArrayList<String> ingrediance ;
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(Json);
            JSONObject jsonObject = jsonArray.getJSONObject(postion);
            JSONArray stepsArray = jsonObject.getJSONArray("ingredients");
            int lenght  = stepsArray.length();
            ingrediance = new ArrayList<>(lenght);
            for (int i = 0 ; i<lenght ; i++){
                JSONObject jsonObject1 = stepsArray.getJSONObject(i);
                String quantity = jsonObject1.getString("quantity");
                String measure = jsonObject1.getString("measure");
                String ingredient = jsonObject1.getString("ingredient");
                String ingredients = quantity + " " + measure + " of " + ingredient ;
                ingrediance.add(ingredients);
            }


        }catch (JSONException e) {
            e.printStackTrace();
            ingrediance = null;
        }
        return ingrediance ;
    }

    public ArrayList<String> getStepsList(String Json , int postion){
        ArrayList<String> steps ;
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(Json);
            JSONObject jsonObject = jsonArray.getJSONObject(postion);
            JSONArray stepsArray = jsonObject.getJSONArray("steps");
            int lenght  = stepsArray.length();
            steps = new ArrayList<>(lenght);
            for (int i = 0 ; i<lenght ; i++){
                JSONObject jsonObject1 = stepsArray.getJSONObject(i);
                String StepShortDescrip = jsonObject1.getString("shortDescription");
                steps.add(StepShortDescrip);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            steps = null;
        }
        return steps;
    }


    public HashMap<String , String> getBakinglist(String Json){
        HashMap<String, String> ListMap ;
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(Json);
            int lenght  = jsonArray.length();
            ListMap = new HashMap<>(lenght);
            for (int i = 0 ; i < lenght ; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String serving = jsonObject.getString("servings");
                ListMap.put(name , serving);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ListMap = null;
        }
        return ListMap;
    }

}
