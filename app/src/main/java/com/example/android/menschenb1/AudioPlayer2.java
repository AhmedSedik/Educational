package com.example.android.menschenb1;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.IOException;

public class AudioPlayer2 extends Activity {

    SeekBar seek_bar;
    ImageView play_button, stop_button;
    MediaPlayer player;
    ImageView exitPlayer;
    Handler seekHandler = new Handler();

    int lessonResId;
    String lessonResUrl;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
            play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
            player = MediaPlayer.create(AudioPlayer2.this, lessonResId);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_audio_player2);





        setFinishOnTouchOutside(false);


        lessonResId = getIntent().getIntExtra("lesson1_res_id", -100);

        seek_bar = (SeekBar) findViewById(R.id.seekbar_1);
        play_button = (ImageView) findViewById(R.id.audio_play_1);
        stop_button = (ImageView) findViewById(R.id.audio_stop_1);

        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        player = MediaPlayer.create(AudioPlayer2.this, lessonResId);
        seek_bar.setMax(player.getDuration());

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlayAudio(lessonResId);

            }
        });
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                releaseMediaPlayer();
                player = MediaPlayer.create(AudioPlayer2.this, lessonResId);

                play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);


            }
        });

        seek_bar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);
                return false;
            }
        });

        seekUpdation();

        exitPlayer = (ImageView) findViewById(R.id.close_audio);
        exitPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                player.stop();
            }
        });
    }


    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();

        }
    };

    public void seekUpdation() {


        seek_bar.setProgress(player.getCurrentPosition());
        seekHandler.postDelayed(run, 1000);
        player.setOnCompletionListener(mCompletionListener);


    }

    //event handler for the progress of seek bar
    private void seekChange(View view) {
        if (player.isPlaying()) {
            SeekBar sb = (SeekBar) view;
            player.seekTo(sb.getProgress());

        }

    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (player != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            player.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            player = null;
        }
    }


    private void PlayAudio(int audioResourceId) {

        audioResourceId = lessonResId;

        if (player == null) {
            player = MediaPlayer.create(AudioPlayer2.this, audioResourceId);
        }

        if (player.isPlaying()) {

            player.pause();
            play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
            try {
                player.prepare();
            } catch (IllegalStateException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            play_button.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);
            player.start();
        }
    }

    private void PlayAudio2(int audioResourceId) {

        audioResourceId = lessonResId;

        if (player == null) {
            player = MediaPlayer.create(AudioPlayer2.this, audioResourceId);
        }

        if (player.isPlaying()) {

            player.pause();
            play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
            try {
                player.prepare();
            } catch (IllegalStateException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            play_button.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);
            player.start();
        }
    }

    // to make sure audio stops when user go back with the back button of the mobilephone
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (player != null) {
            player.stop();
            player.reset();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (player != null) {
            player.pause();
            play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }


}
