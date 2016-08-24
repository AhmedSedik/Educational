package com.example.android.menschenb1;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Lesson1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView kursbuch = (TextView) findViewById(R.id.kurs_buch1);
        assert kursbuch != null;
        kursbuch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lesson1Activity.this,BookView.class);
                startActivity(intent);

            }
        });

        TextView worter = (TextView) findViewById(R.id.lesson1_worter);
        assert worter != null;


        worter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent words_intent = new Intent(Lesson1Activity.this, Lesson1Words.class);
                startActivity(words_intent);
            }
        });


        TextView gramar = (TextView) findViewById(R.id.lesson1_grammar);
        assert gramar != null;
        gramar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Lesson1Activity.this, Lesson1Grammar.class);
                startActivity(intent);
            }
        });

        final TextView audioDateien = (TextView) findViewById(R.id.audio_dateien1);
        assert audioDateien != null;
        audioDateien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Lesson1Activity.this, Lesson1Audio.class);
                startActivity(intent);

            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
