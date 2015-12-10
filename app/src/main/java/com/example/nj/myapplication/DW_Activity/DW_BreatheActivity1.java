package com.example.nj.myapplication.DW_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

import layout.NavigationDrawerFragment;

public class DW_BreatheActivity1 extends AppCompatActivity{
    TextView topTextView, botTextView;
    ImageView centerImage;
    ImageButton backImage;
    Thread timer;
    MediaPlayer narration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dw__breathe1);
        Util.setGlobalFont(this, getWindow().getDecorView());

        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        Toolbar toolbar = (Toolbar) findViewById(R.id.dw_breathe_1_toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawerFragment drawerFragment =(NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.dw3_fragment);
        drawerFragment.setUp(R.id.dw3_fragment, (DrawerLayout) findViewById(R.id.dw3_fragment_navigation_drawer), toolbar, 2);

        backImage = (ImageButton) findViewById(R.id.dw_sec_back2);
        backImage.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_btn_left), 50, 50, false));
        backImage.setBackgroundResource(R.color.transparent);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        topTextView = (TextView) findViewById(R.id.dw_breathe1_topText);
        topTextView.setTextSize(32);
        topTextView.setText(R.string.dw_breathe_title);

        botTextView = (TextView) findViewById(R.id.dw_breathe1_botText);
        botTextView.setTextSize(32);
        botTextView.setText(R.string.dw_4sec);

        centerImage = (ImageView) findViewById(R.id.dw_breathe1_image);
        centerImage.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_breathe_4sec), (int)(deviceWidth*0.75),(int)(deviceWidth*0.75) , false));
        narration= MediaPlayer.create(getApplicationContext(),R.raw.dw_narration_3);
        narration.start();
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(16000);
                    }
                    narration.pause();
                    startActivity(new Intent(DW_BreatheActivity1.this, DW_BreatheActivity2.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timer.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        narration.seekTo(0);
        narration.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        narration.pause();
        timer.interrupt();
        Destory(centerImage);
        Destory(backImage);
    }
    public void Destory(ImageView iv) {

        Drawable d = iv.getDrawable();
        if(d instanceof Drawable)
        {
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            bitmap.recycle();
            bitmap = null;
        }

    }
}
