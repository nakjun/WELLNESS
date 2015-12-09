package com.example.nj.myapplication.DP_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nj.myapplication.MainActivity;
import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

import java.util.Random;

public class DPSelectActivity extends Activity {
    ImageView img,img2;
    Intent i;

    TextView t[]=new TextView[6];
    Bitmap temp;
    public static int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpselect);
        Util.setGlobalFont(this, getWindow().getDecorView());
        i = new Intent(DPSelectActivity.this, DPRateActivity.class);
        status = 0;

        set_onclick_status();

        img2 = (ImageView)findViewById(R.id.imageView4);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_3_flower), MainActivity.width, MainActivity.height*3/4, false);
        img2.setImageBitmap(temp);

        RelativeLayout.LayoutParams params_pink = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params_yellow = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params_green = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params_blue = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //RelativeLayout.LayoutParams params_sky = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //RelativeLayout.LayoutParams params_red = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //params_red.topMargin = MainActivity.height*2/16;
        //params_sky.bottomMargin = MainActivity.height*2/16;

        params_pink.leftMargin = MainActivity.width/4;
        params_pink.topMargin = MainActivity.height*5/16;

        params_yellow.topMargin = MainActivity.height*5/16;
        params_yellow.leftMargin = MainActivity.width*10/16;

        params_green.topMargin = MainActivity.height*8/16;
        params_green.leftMargin = MainActivity.width*10/16;

        params_blue.topMargin = MainActivity.height*8/16;
        params_blue.leftMargin = MainActivity.width/4;

        //t[0].setLayoutParams(params_red);
        t[1].setLayoutParams(params_yellow);
        t[2].setLayoutParams(params_green);
//        t[3].setLayoutParams(params_sky);
        t[4].setLayoutParams(params_blue);
        t[5].setLayoutParams(params_pink);

        img = (ImageView)findViewById(R.id.image_dpdescript);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dp_descript), MainActivity.width, MainActivity.height/4, false);
        img.setImageBitmap(temp);

    }

    void set_onclick_status()
    {
        t[0]=(TextView)findViewById(R.id.TextView_red);
        t[1]=(TextView)findViewById(R.id.TextView_yellow);
        t[2]=(TextView)findViewById(R.id.TextView_green);
        t[3]=(TextView)findViewById(R.id.TextView_sky);
        t[4]=(TextView)findViewById(R.id.TextView_blue);
        t[5]=(TextView)findViewById(R.id.TextView_pink);

        t[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 0;
                startActivity(i);
                finish();
            }
        });
        t[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 1;
                startActivity(i);
                finish();
            }
        });

        t[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 2;
                startActivity(i);
                finish();
            }
        });
        t[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 3;
                startActivity(i);
                finish();
            }
        });
        t[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 4;
                startActivity(i);
                finish();
            }
        });
        t[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 5;
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dpselect, menu);
        return true;
    }

    @Override
    protected void onDestroy() {

        Destory(img);

        super.onDestroy();
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
