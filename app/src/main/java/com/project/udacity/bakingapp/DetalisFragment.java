package com.project.udacity.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class DetalisFragment extends Fragment {

    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private TextView StepDescriptionTextView ;
    String mVideoUrl ;
    String mDescription ;

    public DetalisFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null){
            mDescription = savedInstanceState.getString("mDescription");
            mVideoUrl = savedInstanceState.getString("mVideoUrl");
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalis, container, false);
        if (view.findViewById(R.id.stepLongdescription) != null) {
            simpleExoPlayerView = view.findViewById(R.id.playerView);
            StepDescriptionTextView = view.findViewById(R.id.stepLongdescription);
            simpleExoPlayerView.requestFocus();
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getContext(), "Bakingapp");
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(mVideoUrl),
                    new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            StepDescriptionTextView.setText(mDescription);
            return view;
        }else {
            simpleExoPlayerView = view.findViewById(R.id.playerView);
            simpleExoPlayerView.requestFocus();
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getContext(), "Bakingapp");
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(mVideoUrl),
                    new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            return view;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("mVideoUrl" , mVideoUrl);
        outState.putString("mDescription" , mDescription);
    }

    void setVideoUrl (String VideoUrl ){
        mVideoUrl = VideoUrl;
    }
    void setmDescription(String Description){
        mDescription = Description;
    }

}
