package com.example.android.menschenb1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Lesson2Audio extends AppCompatActivity {

    ImageView audioDateien1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2_audio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        audioDateien1 = (ImageView) findViewById(R.id.lesson2_audio_buch1);
        audioDateien1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPopupActivity(R.raw.l02_audio1);
            }
        });
    }

    private void OpenPopupActivity(int resId){
        Intent audio_intent = new Intent(getApplicationContext(),AudioPlayer.class);
        audio_intent.putExtra("lesson1_res_id",resId);
        startActivity(audio_intent);
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
