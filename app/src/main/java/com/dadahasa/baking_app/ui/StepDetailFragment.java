package com.dadahasa.baking_app.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dadahasa.baking_app.R;
import com.dadahasa.baking_app.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.gson.Gson;

public class StepDetailFragment extends Fragment {

    private PlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;

    String description;
    String thumbnailURL;
    String videoURL;


    //mandatory empty constructor
    public StepDetailFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);

        //Retrieve the step data from the bundle
        if (getArguments() != null){
            String stepJson = getArguments().getString("stepJson");
            //de-serialize step object
            Gson gson = new Gson();
            Step step = gson.fromJson(stepJson, Step.class);

            description = step.getDescription();
            thumbnailURL = step.getThumbnailURL();
            videoURL = step.getVideoURL();
        }

        TextView textView = view.findViewById(R.id.step_detail_view);
        textView.setText(description + "\n" + thumbnailURL + "\n" + videoURL);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO
            }
        });

        //add more views/widgets here

        //initialize the player view
        mPlayerView = view.findViewById(R.id.playerView);
        initializePlayer(videoURL);

        //If there is no video link, remove exoplayer
        if (videoURL == "") {
            mPlayerView.setVisibility(View.GONE);
        }
       return view;
    }


    private void initializePlayer(String mediaUriStr) {
        if (mExoPlayer == null) {

            // Create an instance of the ExoPlayer.
            RenderersFactory renderersFactory = new DefaultRenderersFactory(getContext());
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();

            mExoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            mExoPlayer.setPlayWhenReady(true);

            // Prepare the MediaSource.
            Uri mediaUri = Uri.parse(mediaUriStr);
            if (mediaUri == null){
                mPlayerView.setVisibility(View.INVISIBLE);
            }
            MediaSource mediaSource = buildMediaSource(mediaUri);
            mExoPlayer.prepare(mediaSource, true, false);
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("StepDetailFragment")).
                createMediaSource(uri);
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        releasePlayer();
    }

}

