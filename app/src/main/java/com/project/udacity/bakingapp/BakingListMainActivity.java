package com.project.udacity.bakingapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

public class BakingListMainActivity extends AppCompatActivity implements MasterListFragment.OnListFragmentInteractionListener {
    String mVieosUrls ;
    String mStepDescribtion ;
    ArrayList<String> mSteps ;
    ArrayList<String> mIngrediences ;
    String recepename ;
    String JsonRespons ;
    int recepposition ;
    String Stepthumbnail ;
    boolean mTwoPane ;
    int StepPosition = 0 ;
    BakingListJSON bakingListJSON ;
    DetalisFragment detalisFragment ;
    android.support.v4.app.FragmentManager fragmentManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_list_main);

        if (savedInstanceState != null){
            JsonRespons = savedInstanceState.getString("JsonRespons");
            recepposition = savedInstanceState.getInt("recepposition");
        }

        Intent intent = getIntent();
        if (intent != null){
            JsonRespons = intent.getStringExtra("Json");
            recepposition = intent.getIntExtra("position" , 0);
            bakingListJSON = new BakingListJSON();
            mSteps = bakingListJSON.getStepsList(JsonRespons , recepposition);
            mIngrediences = bakingListJSON.getIngerdiance(JsonRespons , recepposition);
            Stepthumbnail = bakingListJSON.getStepthumbnailURL(JsonRespons , recepposition , StepPosition);
            mVieosUrls = bakingListJSON.getVideoUrl(JsonRespons , recepposition , StepPosition);
            mStepDescribtion = bakingListJSON.getLongDescription(JsonRespons , recepposition , StepPosition);
            recepename = bakingListJSON.getrecepeName(JsonRespons , recepposition) ;
        }

        if (findViewById(R.id.twopanDevider) != null){
            mTwoPane = true;
            if (savedInstanceState == null) {

                fragmentManager = getSupportFragmentManager();
                MasterListFragment masterListFragment = new MasterListFragment();
                masterListFragment.setSteps(mSteps);
                masterListFragment.setingrediences(mIngrediences);
                masterListFragment.setRecepeName(recepename);
                fragmentManager.beginTransaction()
                        .add(R.id.master_list_fragment_container, masterListFragment)
                        .commit();

                detalisFragment = new DetalisFragment();
                detalisFragment.setVideoUrl(mVieosUrls);
                detalisFragment.setmDescription(mStepDescribtion);
                detalisFragment.setStepthumbnailURL(Stepthumbnail);
                fragmentManager.beginTransaction()
                        .add(R.id.Detalis_Contaner, detalisFragment)
                        .commit();
            }
        }else {
            mTwoPane = false;
            if (savedInstanceState == null) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                MasterListFragment masterListFragment = new MasterListFragment();
                masterListFragment.setSteps(mSteps);
                masterListFragment.setingrediences(mIngrediences);
                masterListFragment.setRecepeName(recepename);
                fragmentManager.beginTransaction()
                        .add(R.id.master_list_fragment_container, masterListFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onListFragmentInteraction(int position) {
        if (mTwoPane == true){
            DetalisFragment detalisFragmentnew = new DetalisFragment();
            StepPosition = position ;
            mVieosUrls = bakingListJSON.getVideoUrl(JsonRespons , recepposition , StepPosition);
            mStepDescribtion = bakingListJSON.getLongDescription(JsonRespons , recepposition , StepPosition);
            Stepthumbnail = bakingListJSON.getStepthumbnailURL(JsonRespons , recepposition , StepPosition);
            detalisFragmentnew.setVideoUrl(mVieosUrls);
            detalisFragmentnew.setmDescription(mStepDescribtion);
            detalisFragmentnew.setStepthumbnailURL(Stepthumbnail);
            fragmentManager.beginTransaction()
                    .replace(R.id.Detalis_Contaner, detalisFragmentnew)
                    .commit();

        }else {
            Intent intent = new Intent(this, BakingListDetalisActivity.class);
            intent.putExtra("json", JsonRespons);
            intent.putExtra("position", position);
            intent.putExtra("recepposition", recepposition);
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("JsonRespons", JsonRespons);
        outState.putInt("recepposition", recepposition);

    }
}
