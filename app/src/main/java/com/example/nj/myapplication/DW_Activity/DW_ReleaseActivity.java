package com.example.nj.myapplication.DW_Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.nj.myapplication.R;

import java.util.ArrayList;

public class DW_ReleaseActivity extends AppCompatActivity {
    ArrayList<Bitmap> cenImgBitmap;
    ImageView cenImg;
    MediaPlayer player;
    static int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dw__release);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dw_release_toolbar);
        setTitle("");
        int id = R.drawable.dw_release_human_1;
        cenImgBitmap = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Bitmap temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), id++),433, 1000, false);
            cenImgBitmap.add(temp);
        }



        cenImg = (ImageView) findViewById(R.id.dw_release_cenImg);
        final int timings[] = {46000, 65000, 67000, 79000, 72000, 90000, 95000, 38000};
        Thread timer = new Thread(new Runnable() {
            @Override
            synchronized public void run() {
                int soundInitialIndex=R.raw.dw_release_1;
                player = MediaPlayer.create(getApplicationContext(), R.raw.dw_release_ready);
                player.start();
                try {
                    wait(13000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 8; i++) {
                    player=MediaPlayer.create(getApplicationContext(), soundInitialIndex++);
                    player.start();
                    cenImg.post(new Runnable() {
                        @Override
                        public void run() {
                            cenImg.setImageBitmap(cenImgBitmap.get(index++));
                        }
                    });
                    try {
                        wait(timings[i]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        timer.start();
    }

}
