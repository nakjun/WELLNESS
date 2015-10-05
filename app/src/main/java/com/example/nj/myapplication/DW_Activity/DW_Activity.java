package com.example.nj.myapplication.DW_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nj.myapplication.R;

public class DW_Activity extends Activity {
    TextView title;
    ImageView image1,image2,image3,image4;
    Button button;
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
        image1.setImageResource(R.drawable.dw_main1);
        image2.setImageResource(R.drawable.dw_main2);
        image3.setImageResource(R.drawable.dw_main3);
        image4.setImageResource(R.drawable.dw_main4);

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

