package com.example.android.menschenb1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SplashScreenActivity extends AppCompatActivity {

    public LinearLayout linearLayout;
    public static int splashImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        linearLayout = (LinearLayout) findViewById(R.id.layout_splashscreen);

        if(splashImage==0){
            linearLayout.setBackgroundResource(R.drawable.splash);
        }else {
            linearLayout.setBackgroundResource(splashImage);

        }
        displaySplash.start();

    }

    Thread displaySplash = new Thread(){
        @Override
        public void run() {

            try {
                sleep(1*1000);
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            finally {
                //kill the app
                finish();
            }
        }
    };



}
