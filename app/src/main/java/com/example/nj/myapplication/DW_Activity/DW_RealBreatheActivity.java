package com.example.nj.myapplication.DW_Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.nj.myapplication.R;

public class DW_RealBreatheActivity extends AppCompatActivity {
    ImageView cenImg;
    Thread changerThread;
    Bitmap one,two,three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dw_real_breathe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dw_real_breathe_toolbar);
        toolbar.setTitle("");

        cenImg=(ImageView)findViewById(R.id.dw_real_breathe_cenImg);
        one=(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_breathe_4sec), 900, 1000, false));
        two=(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_breathe_7sec), 900, 1000, false));
        three=(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_breathe_8sec), 900, 1000, false));

        changerThread=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<3;i++){
                    cenImg.setImageBitmap(one);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    

                }
                //startActivity(new Intent(DW_RealBreatheActivity.this, ***.class));
            }
        });
        changerThread.start();

    }

}
