package com.project.udacity.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;


public class DetalisFragment extends Fragment {

    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private TextView StepDescriptionTextView ;
    private ImageView Stepthumbnai ;
    String StepthumbnailURL ;
    String mVideoUrl ;
    String mDescription ;
    long mExoPlayerPosition = C.TIME_UNSET;


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
            StepthumbnailURL = savedInstanceState.getString("StepthumbnailURL");
            mExoPlayerPosition = savedInstanceState.getLong("mExoPlayerPosition" , C.TIME_UNSET);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalis, container, false);
        if (view.findViewById(R.id.stepLongdescription) != null) {
            simpleExoPlayerView = view.findViewById(R.id.playerView);
            Stepthumbnai = view.findViewById(R.id.Stepthumbnail);
            StepDescriptionTextView = view.findViewById(R.id.stepLongdescription);
            simpleExoPlayerView.requestFocus();
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getContext(), "Bakingapp");
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(mVideoUrl),
                    new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            if (mExoPlayerPosition != C.TIME_UNSET) mExoPlayer.seekTo(mExoPlayerPosition);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            StepDescriptionTextView.setText(mDescription);
            if (StepthumbnailURL.length() != 0){
                Picasso.with(getContext()).load(StepthumbnailURL).into(Stepthumbnai);
            }else {
                Stepthumbnai.setVisibility(View.GONE);
            }
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
            if (mExoPlayerPosition != C.TIME_UNSET) mExoPlayer.seekTo(mExoPlayerPosition);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            return view;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null){
            mExoPlayerPosition = mExoPlayer.getCurrentPosition();
            releasePlayer();
        }
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("mVideoUrl" , mVideoUrl);
        outState.putString("mDescription" , mDescription);
        outState.putString("StepthumbnailURL", StepthumbnailURL);
        outState.putLong("mExoPlayerPosition" , mExoPlayer.getCurrentPosition());
    }

    void setVideoUrl (String VideoUrl ){
        mVideoUrl = VideoUrl;
    }
    void setmDescription(String Description){
        mDescription = Description;
    }
    void setStepthumbnailURL(String stepthumbnailURL){
     StepthumbnailURL = stepthumbnailURL  ;
    }


}
