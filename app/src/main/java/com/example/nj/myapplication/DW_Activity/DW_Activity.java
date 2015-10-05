package com.example.nj.myapplication.DW_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nj.myapplication.R;

public class DW_Activity extends Activity {
    TextView title;
    ImageView image1,image2,image3,image4;
    Button button;
    int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dw_);
        title=(TextView)findViewById(R.id.dw_main_title);
        title.setText(R.string.dw_title);
        title.setTextSize(32);

        image1=(ImageView)findViewById(R.id.dw_main_image1);
        image2=(ImageView)findViewById(R.id.dw_main_image2);
        image3=(ImageView)findViewById(R.id.dw_main_image3);
        image4=(ImageView)findViewById(R.id.dw_main_image4);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        image1.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_main1), width/2, width/2, false));
        image2.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_main2), width/2, width/2, false));
        image3.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_main3), width/2, width/2, false));
        image4.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dw_main4), width/2, width/2, false));

        button=(Button)findViewById(R.id.dw_main_button);
        button.setTextSize(32);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DW_Activity.this,DW_secondActivity.class));
            }
        });
        //TODO create activity


    }



}

