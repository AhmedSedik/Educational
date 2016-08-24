package com.example.android.menschenb1;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zozz on 7/28/2016.
 */
public class AudioPlayer extends AppCompatActivity {

    private double startTime = 0;
    private double finalTime = 0;
    public static int oneTimeOnly = 0;
    TextView currentLength;
    TextView songLength;

    SeekBar seek_bar;
    ImageView play_button, stop_button;
    MediaPlayer player;
    ImageView exitPlayer;
    Handler seekHandler = new Handler();
    AudioManager audioManager;
    int lessonResId;
    String lessonResUrl;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
            play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
            player = MediaPlayer.create(AudioPlayer.this, lessonResId);
        }
    };
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        player.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        player.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        player.pause();
                        play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
                    }
                }
            };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.audio_player);
        //make sure screen doesn't go off when player is running only when stoped or released
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setFinishOnTouchOutside(false);
        //setting the audio manager for handling audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        lessonResId = getIntent().getIntExtra("lesson1_res_id", -100);
        currentLength = (TextView) findViewById(R.id.current_length);
        songLength = (TextView) findViewById(R.id.song_length);
        seek_bar = (SeekBar) findViewById(R.id.seekbar_1);
        play_button = (ImageView) findViewById(R.id.audio_play_1);
        stop_button = (ImageView) findViewById(R.id.audio_stop_1);

        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        seekUpdation();
        //play the audio once the activity starts
        if (oneTimeOnly == 0) {
            PlayAudio2(lessonResId);
        }

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlayAudio2(lessonResId);

            }
        });
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                releaseMediaPlayer();
                player = MediaPlayer.create(AudioPlayer.this, lessonResId);
                play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


            }
        });

        seek_bar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);
                return false;
            }
        });

        exitPlayer = (ImageView) findViewById(R.id.close_audio);
        exitPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                player.stop();
                player.reset();

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


        currentLength.setText(String.format("%d min:%d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) startTime))));

        seek_bar.setProgress(player.getCurrentPosition());
        seekHandler.postDelayed(run, 1000);
        startTime = player.getCurrentPosition();
        player.setOnCompletionListener(mCompletionListener);
    }

    //event handler for the progress of seek bar
    private void seekChange(View view) {
        if (player.isPlaying()) {
            SeekBar sb = (SeekBar) view;
            player.seekTo(sb.getProgress());

        }
    }

    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (player != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            player.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            player = null;

            audioManager.abandonAudioFocus(afChangeListener);
        } else {
            Log.i("Media player", "Released");
        }
    }

    private void PlayAudio2(int audioResourceId) {

        audioResourceId = lessonResId;


        if (player == null) {
            player = MediaPlayer.create(AudioPlayer.this, audioResourceId);
        }

        if (player.isPlaying()) {

            player.pause();
            play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            try {
                player.prepare();
            } catch (IllegalStateException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            // Request audio focus for playback
            int result = audioManager.requestAudioFocus(afChangeListener,
                    // Use the music stream.
                    AudioManager.STREAM_MUSIC,
                    // Request permanent focus.
                    AudioManager.AUDIOFOCUS_GAIN);


            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // Start playback.
                play_button.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);
                player.start();
            }
        }
        finalTime = player.getDuration();
        startTime = player.getCurrentPosition();

        seek_bar.setMax((int) finalTime);

        setSongLength();

        currentLength.setText(String.format("%d min:%d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));

        seek_bar.setProgress((int) startTime);
        seekHandler.postDelayed(run, 1000);
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
    protected void onPause() {
        super.onPause();

        if (player.isPlaying()) {
            player.pause();
            play_button.setImageResource(R.drawable.ic_play_circle_filled_black_48dp);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt("position", player.getCurrentPosition());

        if (player.isPlaying()) {
            player.pause();
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        int position = savedInstanceState.getInt("position");
        player.seekTo(position);
        setSongLength();

        play_button.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //a method handles the audio total length displayed in the textview
    private void setSongLength() {
        finalTime = player.getDuration();
        songLength.setText(String.format("%d min : %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
        );
    }
}