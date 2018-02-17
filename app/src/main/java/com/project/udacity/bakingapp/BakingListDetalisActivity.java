package com.project.udacity.bakingapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BakingListDetalisActivity extends AppCompatActivity {
    String JsonRespons ;
    String mVieosUrls ;
    String mStepDescribtion ;
    int recepposition ;
    int StepPosition ;
    int stepsNum;
    String Stepthumbnail ;
    BakingListJSON bakingListJSON ;

    @BindView(R.id.NextStep) Button NextStepButton ;
    @BindView(R.id.PreviousStep) Button PreviousButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_list_detalis);
        Intent intent = getIntent();
        if (intent != null){
            JsonRespons = intent.getStringExtra("json");
            recepposition = intent.getIntExtra("recepposition" , 0);
            StepPosition = intent.getIntExtra("position" , 0);
            bakingListJSON = new BakingListJSON();
            mVieosUrls = bakingListJSON.getVideoUrl(JsonRespons , recepposition , StepPosition);
            Stepthumbnail = bakingListJSON.getStepthumbnailURL(JsonRespons , recepposition , StepPosition);
            mStepDescribtion = bakingListJSON.getLongDescription(JsonRespons , recepposition , StepPosition);
            stepsNum = bakingListJSON.getStepsNum(JsonRespons , recepposition) ;
        }
        final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            DetalisFragment detalisFragment = new DetalisFragment();
            detalisFragment.setVideoUrl(mVieosUrls);
            detalisFragment.setStepthumbnailURL(Stepthumbnail);
            detalisFragment.setmDescription(mStepDescribtion);
            fragmentManager.beginTransaction()
                    .add(R.id.Detalis_Contaner, detalisFragment)
                    .commit();
        }

        if (findViewById(R.id.NextStep) != null) {
            ButterKnife.bind(this);
            NextStepButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stepsNum = bakingListJSON.getStepsNum(JsonRespons, recepposition);

                    if (StepPosition == stepsNum - 1) {
                        Toast.makeText(view.getContext(), "this is the last step ", Toast.LENGTH_SHORT).show();
                    } else {
                        DetalisFragment detalisFragmentnew = new DetalisFragment();
                        StepPosition = StepPosition + 1;
                        mVieosUrls = bakingListJSON.getVideoUrl(JsonRespons, recepposition, StepPosition);
                        Stepthumbnail = bakingListJSON.getStepthumbnailURL(JsonRespons , recepposition , StepPosition);
                        mStepDescribtion = bakingListJSON.getLongDescription(JsonRespons, recepposition, StepPosition);
                        detalisFragmentnew.setVideoUrl(mVieosUrls);
                        detalisFragmentnew.setStepthumbnailURL(Stepthumbnail);
                        detalisFragmentnew.setmDescription(mStepDescribtion);
                        fragmentManager.beginTransaction()
                                .replace(R.id.Detalis_Contaner, detalisFragmentnew)
                                .commit();


                    }
                }
            });
            PreviousButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (StepPosition == 0) {
                        Toast.makeText(view.getContext(), "this is the first step ", Toast.LENGTH_SHORT).show();
                    } else {
                        DetalisFragment detalisFragmentnew = new DetalisFragment();
                        StepPosition = StepPosition - 1;
                        mVieosUrls = bakingListJSON.getVideoUrl(JsonRespons, recepposition, StepPosition);
                        Stepthumbnail = bakingListJSON.getStepthumbnailURL(JsonRespons , recepposition , StepPosition);
                        mStepDescribtion = bakingListJSON.getLongDescription(JsonRespons, recepposition, StepPosition);
                        detalisFragmentnew.setVideoUrl(mVieosUrls);
                        detalisFragmentnew.setmDescription(mStepDescribtion);
                        detalisFragmentnew.setStepthumbnailURL(Stepthumbnail);
                        fragmentManager.beginTransaction()
                                .replace(R.id.Detalis_Contaner, detalisFragmentnew)
                                .commit();
                    }
                }
            });
        }
    }
}
