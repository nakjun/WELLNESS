package com.example.nj.myapplication.DW_Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nj.myapplication.R;

public class DW_secondActivity extends AppCompatActivity {
    TextView top,mid,bot;
    ImageButton backImage, centerImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dw_second);
        top=(TextView)findViewById(R.id.dw_sec_topText);
        mid=(TextView)findViewById(R.id.dw_sec_midText);
        bot=(TextView)findViewById(R.id.dw_sec_botText);

        backImage =(ImageButton)findViewById(R.id.dw_sec_back);
        backImage.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.common_back_button), 100, 100, false));
        backImage.setBackgroundResource(R.color.transparent);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        centerImage=(ImageButton)findViewById(R.id.dW_sec_cenImg);
        centerImage.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_sec_cen), 500, 500, false));
        centerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DW_secondActivity.this,DW_BreatheActivity1.class));
            }
        });
        top.setText(R.string.dw_2nd_top);
        top.setTextSize(32);
        mid.setText(R.string.dw_2nd_mid);
        mid.setTextSize(32);
        bot.setText(R.string.dw_2nd_bottom);

    }



}
