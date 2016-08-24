package com.example.android.menschenb1;

import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

public class Lesson1Words extends AppCompatActivity {


    private MediaPlayer mMediaPlayer;
    private ImageView imageBig;
    private ImageView playSound;
    private ImageView nextImage;
    private ImageView prevImage;

    private int i;


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            playSound.setImageResource(R.drawable.ic_play_circle_outline_white_24dp);
            releaseMediaPlayer();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ArrayList<Word> lessone1Words = new ArrayList<>();

        lessone1Words.add(new Word("vernünftig", R.drawable.vernunftig, R.raw.l01_vernunftig));
        lessone1Words.add(new Word("großzügig", R.drawable.groszugig, R.raw.l01_grosszugig));
        lessone1Words.add(new Word("fair", R.drawable.fair, R.raw.l01_fair));
        lessone1Words.add(new Word("intelligent/klug", R.drawable.intelligent, R.raw.l01_intelligent));
        lessone1Words.add(new Word("frech", R.drawable.frech, R.raw.l01_frech));
        lessone1Words.add(new Word("kreativ", R.drawable.kreaiv, R.raw.l01_kreativ));
        lessone1Words.add(new Word("sparsam", R.drawable.sparsam, R.raw.l01_sparsam));
        lessone1Words.add(new Word("realistisch", R.drawable.realistisch, R.raw.l01_realistisch));
        lessone1Words.add(new Word("hübsch", R.drawable.hubsch, R.raw.l01_hubsch));
        lessone1Words.add(new Word("aufmerksam", R.drawable.aufmerksam, R.raw.l01_aufmerksam));
        lessone1Words.add(new Word("mutig", R.drawable.mutig, R.raw.l01_mutig));
        lessone1Words.add(new Word("nervös", R.drawable.nervos, R.raw.l01_nervos));
        lessone1Words.add(new Word("kritisch", R.drawable.kritisch, R.raw.l01_kritisch));
        lessone1Words.add(new Word("treu", R.drawable.treu, R.raw.l01_treu));
        lessone1Words.add(new Word("ernst", R.drawable.ernst, R.raw.l01_ernst));
        lessone1Words.add(new Word("ordentlich", R.drawable.ordentlich, R.raw.l01_ordentlich));
        lessone1Words.add(new Word("streng", R.drawable.streng, R.raw.l01_streng));


        final WordAdapter adapter = new WordAdapter(this, lessone1Words, R.color.lesson1_color);
        final ListView listView = (ListView) findViewById(R.id.word_list);
        assert listView != null;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int position, long l) {

                final Word word = lessone1Words.get(position);

                //open Popup layout for the image @but still can't iterate through the arraylist
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);

                View popupView = layoutInflater.inflate(R.layout.popup, null);


                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        AppBarLayout.LayoutParams.WRAP_CONTENT,
                        AppBarLayout.LayoutParams.WRAP_CONTENT);

                // when touch outside the Popup layout it dismiss it
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);


                //change image in ImageView of Popup.xml to current image in the array
                //final ImageView imageBig = (ImageView) popupWindow.getContentView().findViewById(R.id.popimage);
                imageBig = (ImageView) popupView.findViewById(R.id.popimage);

                imageBig.setImageResource(word.getImageResourceId());

                Button closeImage = (Button) popupView.findViewById(R.id.close_image);
                closeImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        releaseMediaPlayer();
                    }
                });

                // button on the Popup which plays the sound of the word
                playSound = (ImageView) popupView.findViewById(R.id.play_image);
                playSound.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //when playing the sound set the position to whatever i= position  is
                        Word word3 = lessone1Words.get(i);
                        //responsible for the playback of the audio according to the arraylist index
                        releaseMediaPlayer();

                        mMediaPlayer = MediaPlayer.create(Lesson1Words.this, word3.getAudioResourceId());
                        mMediaPlayer.setVolume(0.5f, 0.5f);
                        mMediaPlayer.start();
                        playSound.setImageResource(R.drawable.ic_pause_circle_outline_white_48dp);
                        mMediaPlayer.setOnCompletionListener(mCompletionListener);

                    }
                });

                popupWindow.showAtLocation(listView, Gravity.CENTER, 0, 0);
                i = position;
                imageBig.setTag(i);

                nextImage = (ImageView) popupView.findViewById(R.id.next_image);


                nextImage.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //makesure the prev butt is visible when going going from 0 index
                        prevImage.setVisibility(View.VISIBLE);

                        if (i != (lessone1Words.size()) - 1) {

                            //release the media after pressing next to be ready for the next media
                            releaseMediaPlayer();
                            //make sure the play icon appears and not stuck at pause icon
                            playSound.setImageResource(R.drawable.ic_play_circle_outline_white_24dp);
                            i = (int) imageBig.getTag();
                            i++;
                            imageBig.setTag(i);

                            Word word1 = lessone1Words.get(i);
                            imageBig.setImageResource(word1.getImageResourceId());
                            mMediaPlayer = MediaPlayer.create(Lesson1Words.this, word1.getAudioResourceId());

                        } else {
                            nextImage.setVisibility(View.INVISIBLE);
                        }

                    }
                });
                prevImage = (ImageView) popupView.findViewById(R.id.prev_image);
                prevImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //makesure the next butt is visible when going back from last index
                        nextImage.setVisibility(View.VISIBLE);
                        if (i != 0) {
                            //release the media after pressing next to be ready for the next media
                            releaseMediaPlayer();
                            //make sure the play icon appears and not stuck at pause icon
                            playSound.setImageResource(R.drawable.ic_play_circle_outline_white_24dp);
                            i = (int) imageBig.getTag();
                            i--;
                            imageBig.setTag(i);
                            Word word1 = lessone1Words.get(i);
                            imageBig.setImageResource(word1.getImageResourceId());
                            mMediaPlayer = MediaPlayer.create(Lesson1Words.this, word1.getAudioResourceId());
                        } else {

                            prevImage.setVisibility(View.INVISIBLE);
                        }
                    }
                });

//end of Popup


            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
