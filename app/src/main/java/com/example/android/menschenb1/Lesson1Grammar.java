package com.example.android.menschenb1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Lesson1Grammar extends AppCompatActivity {

    ImageView audio1;
    ImageView audio2;
    ImageView audio3;
    ImageView audio4;
    ImageView audio5;
    ImageView audio6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1_grammar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        audio1 = (ImageView) findViewById(R.id.lesson1_audio_gramar_1);
        assert audio1 != null;
        audio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPopupActivity(R.raw.l01_audiotraining_grammatik_01);
            }
        });
        audio2 = (ImageView) findViewById(R.id.lesson1_audio_gramar_2);
        assert audio2 != null;
        audio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPopupActivity(R.raw.l01_audiotraining_grammatik_02);
            }

        });
        audio3 = (ImageView) findViewById(R.id.lesson1_audio_gramar_3);
        assert audio3 != null;
        audio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPopupActivity(R.raw.l01_audiotraining_grammatik_03);
            }
        });
        audio4 = (ImageView) findViewById(R.id.lesson1_audio_gramar_4);
        audio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPopupActivity(R.raw.l01_audiotraining_grammatik_04);
            }
        });
        audio5 = (ImageView) findViewById(R.id.lesson1_audio_gramar_5) ;
        audio5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPopupActivity(R.raw.l01_audiotraining_kommunikation_01);
            }
        });
        audio6=(ImageView) findViewById(R.id.lesson1_audio_gramar_6);
        audio6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPopupActivity(R.raw.l01_audiotraining_kommunikation_02);
            }
        });


    }

    private void OpenPopupActivity(int resId){
        Intent audio_intent = new Intent(getApplicationContext(),AudioPlayer.class);
        audio_intent.putExtra("lesson1_res_id",resId);
        startActivity(audio_intent);
    }
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
