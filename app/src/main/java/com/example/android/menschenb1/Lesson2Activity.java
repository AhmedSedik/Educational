package com.example.android.menschenb1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class Lesson2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView worter = (TextView) findViewById(R.id.lesson2_worter);
        assert worter != null;

        worter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent words2_intent = new Intent(Lesson2Activity.this, Lesson2Words.class);
                startActivity(words2_intent);
            }
        });

        TextView gramar = (TextView) findViewById(R.id.lesson2_grammar);
        assert gramar!=null;
        gramar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lesson2Activity.this, Lesson2Grammar.class);
                startActivity(intent);
            }
        });

        final TextView audioDateien = (TextView) findViewById(R.id.audio_dateien2);
        assert audioDateien !=null;
        audioDateien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Lesson2Activity.this, Lesson2Audio.class);
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