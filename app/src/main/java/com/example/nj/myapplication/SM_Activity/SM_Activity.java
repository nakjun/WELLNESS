package com.example.nj.myapplication.SM_Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nj.myapplication.DW_Activity.DW_BreatheActivity2;
import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

public class SM_Activity extends AppCompatActivity {
    ImageButton imagebtn;
    ImageView centerImage;
    Bitmap centerImageBitmap,btnBitmap;
    TextView title,text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm_activity);
        Util.setGlobalFont(this, getWindow().getDecorView());
        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;

        centerImage=(ImageView)findViewById(R.id.sm_main_centerimage);
        centerImageBitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_main_1), deviceWidth, deviceHeight, false);
        centerImage.setImageBitmap(centerImageBitmap);
        btnBitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_btn_right), 80, 80, false);
        imagebtn=(ImageButton)findViewById(R.id.sm_main_btn);
        imagebtn.setImageBitmap(btnBitmap);
        imagebtn.setBackgroundResource(R.color.transparent);
        centerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SM_Activity.this, SM_Activity_2.class));
            }
        });

        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SM_Activity.this, SM_Activity_2.class));
            }
        });
        Toast toast = Toast.makeText(getApplicationContext(),
                deviceWidth+"", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        title=(TextView)findViewById(R.id.sm_main_title);
        title.setText(R.string.sm_main_title);
        text=(TextView)findViewById(R.id.sm_main_text);
        text.setText(R.string.sm_main_text);
        text.setTextColor(Color.parseColor("#1F497D"));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Destory(centerImage);
        Destory(imagebtn);
        centerImageBitmap.recycle();
        btnBitmap.recycle();
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
