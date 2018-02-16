package com.project.udacity.bakingapp;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements BakinglistAdapter.BakinglistAdapterOnclickHandler{
    private final String The_Recipe_Listing_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private String TheListJsonResponse ;
    private BakinglistAdapter madapter ;
    @BindView(R.id.recyclerView)  RecyclerView mRecyclerView ;
    @BindView(R.id.progress_bar)  ProgressBar mprogressBar ;
    @BindView(R.id.error_text)  TextView merrorMassage ;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TheListJsonResponse" , TheListJsonResponse);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
            mRecyclerView.setVisibility(View.INVISIBLE);
            merrorMassage.setVisibility(View.INVISIBLE);
            madapter = new BakinglistAdapter(this);
            mRecyclerView.setAdapter(madapter);
            if (getResources().getBoolean(R.bool.Istablet)){
               GridLayoutManager gridLayoutManager = new GridLayoutManager(this , 3);
                mRecyclerView.setLayoutManager(gridLayoutManager);
            }else {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(linearLayoutManager);
            }
        if (savedInstanceState == null) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(The_Recipe_Listing_URL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mprogressBar.setVisibility(View.INVISIBLE);
                            merrorMassage.setTextSize(View.VISIBLE);
                        }
                    });

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    TheListJsonResponse = response.body().string();
                    BakingListJSON bakingListJSON = new BakingListJSON();
                    final HashMap<String, String> map = bakingListJSON.getBakinglist(TheListJsonResponse);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            madapter.setBakingList(map);
                            mprogressBar.setVisibility(View.INVISIBLE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                        }
                    });
                }
            });
        }else {
            TheListJsonResponse = savedInstanceState.getString("TheListJsonResponse");
            BakingListJSON bakingListJSON = new BakingListJSON();
            final HashMap<String, String> map = bakingListJSON.getBakinglist(TheListJsonResponse);
            madapter.setBakingList(map);
            mprogressBar.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public void onclick(int position) {
        Intent intent = new Intent(this , BakingListMainActivity.class);
        intent.putExtra("position" , position) ;
        intent.putExtra("Json" ,TheListJsonResponse );
        startActivity(intent);
    }

}
