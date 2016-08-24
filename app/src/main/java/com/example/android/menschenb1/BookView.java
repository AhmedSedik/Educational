package com.example.android.menschenb1;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;

public class BookView extends AppCompatActivity {

    WebView mWebView = null;

    private int [] images = {R.drawable.preview_content1, R.drawable.preview_content2, R.drawable.preview_content3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_book_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mWebView= (WebView) findViewById(R.id.book1);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.loadUrl("http://www.fanseries.fr/wp-content/gallery/allison-mack/allison-mack-01.jpg");




    }

}
