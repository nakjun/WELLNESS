package com.example.nj.myapplication.SM_Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nj.myapplication.R;

import java.util.Calendar;

public class SM_Activity_3 extends AppCompatActivity {
    Calendar cal;
    TextView date;
    ImageButton btn_prev,btn_next;
    Bitmap btn_prev_bitmap,btn_next_bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm__activity_3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sm_3_toolbar);

        setSupportActionBar(toolbar);
        btn_prev=(ImageButton)findViewById(R.id.sm_3_btn_prev);
        btn_next=(ImageButton)findViewById(R.id.sm_3_btn_next);
        btn_prev_bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_btn_left), 80, 80, false);
        btn_next_bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_btn_right), 80, 80, false);
        btn_prev.setImageBitmap(btn_prev_bitmap);
        btn_next.setImageBitmap(btn_next_bitmap);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cal=Calendar.getInstance();
        date=(TextView)findViewById(R.id.sm_3_date);
        String temp=getDayofWeek(cal.get(Calendar.DATE));
        date.setText((cal.get(Calendar.MONTH)+1)+"월 "+cal.get(Calendar.DAY_OF_MONTH)+"일 "+temp+"요일" );
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity();
            }
        });


    }
    String getDayofWeek(int n) {
        String day = "";
        switch (n) {
            case 1:
                day = "일";
                break;
            case 2:
                day = "월";
                break;
            case 3:
                day = "화";
                break;
            case 4:
                day = "수";
                break;
            case 5:
                day = "목";
                break;
            case 6:
                day = "금";
                break;
            case 7:
                day = "토";
                break;
        }
        return day;
    }
}
