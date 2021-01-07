package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning audio manager some variables
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVol= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


//Using seekbar for volume
        SeekBar seekBar= findViewById(R.id.seekBar);
        seekBar.setMax(maxVol);
        seekBar.setProgress(currentVol);

// Listener to change volume when we adjust Seek bar.
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //Overriding method to tell the app to what to do when the state of seek bar is changed.
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.iamthat);





//OnClickListener for buttons to play, pause and stop music
        Button play = (Button)findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"Playing...", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(MainActivity .this,"I'm done!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Button pause = (Button)findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"Paused", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
            }
        });
        Button stop = (Button)findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"Restarting...", Toast.LENGTH_SHORT).show();
                mediaPlayer.seekTo(0);
                mediaPlayer.start();

            }
        });




        //For vdo view
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.video);

        //Controling vdo
        MediaController mediaController = new MediaController(this);

        //Linking MediaConroller to vdo
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

    }
}