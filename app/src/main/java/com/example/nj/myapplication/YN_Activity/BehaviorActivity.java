package com.example.nj.myapplication.YN_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

public class BehaviorActivity extends Activity {

    ImageView btn1,btn2,main;
    //Button behavior1,behavior2;
    Bitmap temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        Util.setGlobalFont(this, getWindow().getDecorView());
        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;

        btn1 = (ImageView)findViewById(R.id.yn_btn1);
        btn2 = (ImageView)findViewById(R.id.yn_btn2);
        main = (ImageView)findViewById(R.id.imageView_Main2);


        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.yn_title2),deviceWidth, deviceHeight/2, false);
        main.setImageBitmap(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.yn_btn1),deviceWidth/2, deviceHeight/5, false);
        btn1.setImageBitmap(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.yn_btn2),deviceWidth/2, deviceHeight/5, false);
        btn2.setImageBitmap(temp);
        //btn1.set

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BehaviorActivity.this,YNActionAcitivity.class));
                //finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BehaviorActivity.this,YN_WEEKLYDATA.class));
                //finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_behavior, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
