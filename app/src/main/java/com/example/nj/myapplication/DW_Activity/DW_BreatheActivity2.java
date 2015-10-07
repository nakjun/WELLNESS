package com.example.nj.myapplication.DW_Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nj.myapplication.R;

public class DW_BreatheActivity2 extends AppCompatActivity {
    TextView topTextView,botTextView;
    ImageView centerImage;
    ImageButton backImage;
    Bitmap centerImageBitmap,backImageBitmap;
    Thread timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dw__breathe2);

        backImageBitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.common_back_button), 100, 100, false);
        backImage =(ImageButton)findViewById(R.id.dw_sec_back3);
        backImage.setImageBitmap(backImageBitmap);
        backImage.setBackgroundResource(R.color.transparent);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        topTextView=(TextView)findViewById(R.id.dw_breathe2_topText);
        topTextView.setTextSize(32);
        topTextView.setText(R.string.dw_breathe_title);

        botTextView=(TextView)findViewById(R.id.dw_breathe2_botText);
        botTextView.setTextSize(32);
        botTextView.setText(R.string.dw_7sec);

        centerImage=(ImageView)findViewById(R.id.dw_breathe2_image);
        centerImageBitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_breathe_7sec), 450, 500, false);
        centerImage.setImageBitmap(centerImageBitmap);

        timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(4000);
                    }
//                    startActivity(new Intent(DW_BreatheActivity2.this, DW_BreatheActivity3.class));
                    centerImageBitmap.recycle();
                    backImageBitmap.recycle();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        timer.start();
    }
}
