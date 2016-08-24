package com.example.android.menschenb1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView lesson1 = (TextView) findViewById(R.id.lesson_1);
        assert lesson1 != null;
        TextView lesson2 = (TextView) findViewById(R.id.lesson_2);
        TextView lesson3 = (TextView) findViewById(R.id.lesson_3);
        TextView lesson4 = (TextView) findViewById(R.id.lesson_4);
        TextView lesson5 = (TextView) findViewById(R.id.lesson_5);
        TextView lesson6 = (TextView) findViewById(R.id.lesson_6);
        TextView lesson7 = (TextView) findViewById(R.id.lesson_7);
        TextView lesson8 = (TextView) findViewById(R.id.lesson_8);

        lesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lesson1Intent = new Intent(MainActivity.this, Lesson1Activity.class);
                startActivity(lesson1Intent);
            }
        });

        lesson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lesson1Intent = new Intent(MainActivity.this, Lesson2Activity.class);
                startActivity(lesson1Intent);

            }
        });
        lesson3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lesson4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lesson5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lesson6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lesson7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lesson8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            String url = "http://www.hueber.de/seite/pg_faq_hlp";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*String url = "http://www.hueber.de/seite/pg_faq_hlp";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));*/
}
