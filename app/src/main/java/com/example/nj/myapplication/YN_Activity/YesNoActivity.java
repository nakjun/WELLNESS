package com.example.nj.myapplication.YN_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

public class YesNoActivity extends Activity {

    ImageView imgYes,imgNo;
    TextView tView;
    String text[]={"대화 할 때 상대방의 눈을 바라본다.","나는 편안하고 바른 자세로 \n상대방의 대화에 집중한다.","적절한 목소리 톤과 빠르기로 말하고\n상대방이 내 말을 쉽게 이해한다.","말하기 전에 생각한다.","상대방의 말을 끊지 않고 \n끝까지 경청한다.","상대방을 비난하는 말을 자주 한다.","상대방이 이야기 할 때\n다른 행동을 한다.","나와 '다름'을 인정하며\n상대방을 존중하며 대화한다.","대화하는 시간을 소중하게 생각한다.","상대방을 칭찬한다."};
    int index=1;
    int max;
    int yes_count=0,no_count=0;
    Intent i;
    public static int deviceWidth,deviceHeight;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_no);
        Util.setGlobalFont(this, getWindow().getDecorView());
        max = text.length;
        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        deviceWidth = displayMetrics.widthPixels;
        deviceHeight = displayMetrics.heightPixels;
        Toast.makeText(getApplicationContext(), deviceWidth+"", Toast.LENGTH_SHORT).show();

        tView = (TextView)findViewById(R.id.textView);
        int Size=20;
        if(deviceWidth==480) Size = 35;
        if(deviceWidth==800) Size = 45;
        if(deviceWidth==1080) Size = 25;
        tView.setTextSize(Size);
        init();
        imgYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < max) {
                    tView.setText(text[index]);
                    index++;
                    yes_count++;
                    Log.d("counted", "yes count : " + yes_count);
                } else {
                    yes_count++;
                    i = new Intent(YesNoActivity.this, SplashActivity2.class);
                    i.putExtra("YesCount", yes_count);
                    startActivity(i);
                    finish();
                }
            }
        });
        imgNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < max) {
                    tView.setText(text[index]);
                    index++;
                    no_count++;
                    Log.d("counted", "no count : " + no_count);
                } else {
                    no_count++;
                    i = new Intent(YesNoActivity.this, SplashActivity2.class);
                    i.putExtra("YesCount", yes_count);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    void init()
    {
        imgYes = (ImageView)findViewById(R.id.yes);
        imgNo = (ImageView)findViewById(R.id.no);

        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.yn_imgyes2),deviceWidth/3, deviceHeight/6, false);
        imgYes.setImageBitmap(bitmap);

        imgYes.setPadding(deviceWidth/10,0,0,deviceHeight/5);

        //ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) imgYes.getLayoutParams();

        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.yn_imgno2),deviceWidth/3, deviceHeight/6, false);
        imgNo.setImageBitmap(bitmap);
        imgNo.setPadding(deviceWidth/20,0,30,deviceHeight/5);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
