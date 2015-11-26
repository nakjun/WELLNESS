package com.example.nj.myapplication.DW_Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

public class DW_RealBreatheActivity extends AppCompatActivity {
    ImageView cenImg;
    Thread changerThread;
    Bitmap one,two,three;
    MediaPlayer player;
    TextView amont;
    int amount=1;
    public void addAmount(){

        amont.setText(amount+"");
        amount++;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dw_real_breathe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dw_real_breathe_toolbar);
        Util.setGlobalFont(this, getWindow().getDecorView());
        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;

        toolbar.setTitle("");
        amont=(TextView)findViewById(R.id.amount);
        cenImg=(ImageView)findViewById(R.id.dw_real_breathe_cenImg);
        one=(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_breathe_4sec),(int)(deviceWidth*0.75),(int)(deviceWidth*0.75), false));
        two=(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_breathe_7sec), (int)(deviceWidth*0.75),(int)(deviceWidth*0.75), false));
        three=(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_breathe_8sec), (int)(deviceWidth*0.75),(int)(deviceWidth*0.75), false));

        amont=(TextView)findViewById(R.id.amount);


        changerThread=new myNewClass();
        changerThread.start();
    }
    class myNewClass extends Thread{
        int count=0;
        @Override
        synchronized public void run() {
            player= MediaPlayer.create(getApplicationContext(), R.raw.dw_narration_breathe_intro);
            player.start();
            try {
                wait(12000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<3;i++){
                count=i+1;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        amont.setText(count+"회");
                    }
                });
                cenImg.post(new Runnable() {
                    @Override
                    public void run() {
                        cenImg.setImageBitmap(one);
                    }
                });

                player= MediaPlayer.create(getApplicationContext(), R.raw.dw_narration_breathe_4sec);
                player.start();
                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cenImg.post(new Runnable() {
                    @Override
                    public void run() {
                        cenImg.setImageBitmap(two);
                    }
                });
                player= MediaPlayer.create(getApplicationContext(), R.raw.dw_narration_breathe_7sec);
                player.start();
                try {
                    wait(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cenImg.post(new Runnable() {
                    @Override
                    public void run() {
                        cenImg.setImageBitmap(three);
                    }
                });
                player= MediaPlayer.create(getApplicationContext(), R.raw.dw_narration_breathe_8sec);
                player.start();
                try {
                    wait(9000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            startActivity(new Intent(DW_RealBreatheActivity.this, DW_ReleaseActivity.class));
        }
    }
}
