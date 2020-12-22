package com.shakibmark.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoPlayer extends AppCompatActivity {

    VideoView videoView;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoView = (VideoView)findViewById(R.id.myPlayer);

        // This Line Use For Full Screen Window
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        position = getIntent().getIntExtra("position",-1);
        getSupportActionBar().hide();
        
        playVideo();
        


    }

    private void playVideo() {

        // Use For Back Forward Video
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController( mediaController );
        videoView.setVideoPath(String.valueOf(MainActivity.fileArrayList.get(position)));
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        // For Auto next Video
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                videoView.setVideoPath(String.valueOf(MainActivity.fileArrayList.get(position = position +1)));
                videoView.start();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        videoView.stopPlayback();
    }
}