package com.example.nj.myapplication.SM_Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.nj.myapplication.R;

public class SM_Activity_2 extends AppCompatActivity {
    ImageButton imagebtn;
    Bitmap btnBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm__activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sm_2_toolbar);
        toolbar.setTitle("\t\t\t\t\t\t\t\t\t\tWELLNESS");
        toolbar.setTitleTextColor(R.color.sm_2_title);
        toolbar.setTitleTextAppearance(getApplicationContext(),R.style.Theme_YourTheme_Styled_ActionBar_TitleTextStyle);
        btnBitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_btn_left), 50, 50, false);
        imagebtn=(ImageButton)findViewById(R.id.sm_2_btn);
        imagebtn.setImageBitmap(btnBitmap);
        imagebtn.setBackgroundResource(R.color.transparent);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setSupportActionBar(toolbar);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Destory(imagebtn);
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
