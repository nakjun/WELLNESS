package com.example.nj.myapplication.DW_Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

import layout.NavigationDrawerFragment;

public class DW_secondActivity extends AppCompatActivity {
    TextView top,mid,bot;
    ImageButton backImage, centerImage;
    MediaPlayer narration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dw_second);
        Util.setGlobalFont(this, getWindow().getDecorView());
        Toolbar toolbar = (Toolbar) findViewById(R.id.dw_2_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ///////////

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawerFragment drawerFragment =(NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.dw2_fragment);
        drawerFragment.setUp(R.id.dw2_fragment, (DrawerLayout) findViewById(R.id.dw2_fragment_navigation_drawer), toolbar, 2);
        /////////

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;

        top=(TextView)findViewById(R.id.dw_sec_topText);
        mid=(TextView)findViewById(R.id.dw_sec_midText);
        bot=(TextView)findViewById(R.id.dw_sec_botText);

        backImage =(ImageButton)findViewById(R.id.dw_sec_back);
        backImage.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_btn_left), 50, 50, false));
        backImage.setBackgroundResource(R.color.transparent);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        centerImage=(ImageButton)findViewById(R.id.dW_sec_cenImg);
        centerImage.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_sec_cen), (int)(deviceWidth*0.5), (int)(deviceWidth*0.5), false));
        centerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                narration.pause();
                startActivity(new Intent(DW_secondActivity.this,DW_BreatheActivity1.class));
            }
        });
        top.setText(R.string.dw_2nd_top);
        top.setTextSize(32);
        mid.setText(R.string.dw_2nd_mid);
        mid.setTextSize(32);
        bot.setText(R.string.dw_2nd_bottom);

        narration= MediaPlayer.create(getApplicationContext(),R.raw.dw_narration_2);
        narration.start();
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
    }

}
