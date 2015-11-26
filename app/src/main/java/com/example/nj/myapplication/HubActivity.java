package com.example.nj.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowAnimationFrameStats;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.nj.myapplication.DP_Activity.DpActivity;
import com.example.nj.myapplication.DW_Activity.DW_Activity;
import com.example.nj.myapplication.SM_Activity.SM_Activity;
import com.example.nj.myapplication.YN_Activity.BehaviorActivity;
import com.example.nj.myapplication.YN_Activity.YesNoActivity;

public class HubActivity extends Activity {
    Intent intent;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        Util.setGlobalFont(this, getWindow().getDecorView());
        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        intent = getIntent();
        ID = intent.getStringExtra("ID");
        ImageView btn_478 = (ImageView)findViewById(R.id.imgView_dw);
        btn_478.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.hub_dw), (int)(width / 2), (int)(width / 2), false));
        ImageView btn_yesno = (ImageView)findViewById(R.id.imgView_yn);
        btn_yesno.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.hub_yn), (int)(width / 2), (int)(width /2), false));
        ImageView btn_dp = (ImageView)findViewById(R.id.imgView_dp);
        btn_dp.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.hub_dp), width / 2, width / 2, false));
        ImageView btn_sm=(ImageView)findViewById(R.id.imgView_sw);
        btn_sm.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.hub_sm), width / 2, width / 2, false));
//        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        params.setMargins((int) (deviceWidth * 0.01), (int) (deviceHeight * 0.01), (int) (deviceWidth * 0.01), (int) (deviceHeight * 0.01));
//        btn_dp.setLayoutParams(params);
//        btn_478.setLayoutParams(params);
//        btn_yesno.setLayoutParams(params);
//        btn_sm.setLayoutParams(params);
        btn_478.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HubActivity.this, DW_Activity.class));
            }
        });
        btn_yesno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HubActivity.this, YesNoActivity.class));
            }
        });
        btn_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HubActivity.this, DpActivity.class));
            }
        });
        btn_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HubActivity.this, SM_Activity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hub, menu);
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
