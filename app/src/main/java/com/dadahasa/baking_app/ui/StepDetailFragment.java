package com.dadahasa.baking_app.ui;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.app.Activity.RESULT_OK;

public class StepDetailFragment extends Fragment {

    private PlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;

    static int stepIndex;
    String stepTitle;
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

        //Retrieve the step data from the bundle that was sent from the StepDetailActivity
        if (getArguments() != null){
            String stepJson = getArguments().getString("stepJson");
            //de-serialize step object
            Gson gson = new Gson();
            Step step = gson.fromJson(stepJson, Step.class);

            stepTitle = step.getShortDescription();
            description = step.getDescription();
            thumbnailURL = step.getThumbnailURL();
            videoURL = step.getVideoURL();

            //get the current adapter's step index from the extras
            stepIndex = getArguments().getInt("stepIndex");
            //Log.d("stepIndex: ",String.valueOf(stepIndex));
        }

        TextView textView = view.findViewById(R.id.step_detail_view);
        //textView.setText(description + "\n" + thumbnailURL + "\n" + videoURL);
        textView.setText(stepTitle + "\n" + "\n" + description);

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Not used. detail step fields are not clickable.
            }
        });

        //add more views/widgets here

        //initialize the player view
        mPlayerView = view.findViewById(R.id.playerView);

        //If there is no video link, remove exoplayer
        if (TextUtils.isEmpty(videoURL)) {
            mPlayerView.setVisibility(View.GONE);
        }else {
            initializePlayer(videoURL);
        }

        //Set the click listener for the bottom navigation (for previous/next step)
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        //get a new intent instance to pass the clicked previous or next step id
                        // to host activity as extras

                        Intent intent = new Intent();

                        switch (item.getItemId()){
                            case R.id.previousStep:
                                //Toast.makeText(getActivity(), "Previous", Toast.LENGTH_SHORT).show();
                                intent.putExtra("MESSAGE",stepIndex-1);
                                getActivity().setResult(RESULT_OK, intent);
                                getActivity().finish();//end this activity and return to calling activity
                                return true;

                            case R.id.nextStep:
                                //Toast.makeText(getActivity(), "Next", Toast.LENGTH_SHORT).show();
                                intent.putExtra("MESSAGE",stepIndex+1);
                                getActivity().setResult(RESULT_OK, intent);
                                getActivity().finish();//end this activity and return to calling activity
                                return true;
                        }
                        return true;
                    }

                }
        );

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

