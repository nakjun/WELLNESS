package com.example.nj.myapplication.DW_Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nj.myapplication.R;

public class DW_secondActivity extends AppCompatActivity {
    TextView top,mid,bot;
    ImageButton back,center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dw_second);
        top=(TextView)findViewById(R.id.dw_sec_topText);
        mid=(TextView)findViewById(R.id.dw_sec_midText);
        bot=(TextView)findViewById(R.id.dw_sec_botText);
        top.setText(R.string.dw_2nd_top);
        mid.setText(R.string.dw_2nd_mid);
        mid.setTextSize(32);
        bot.setText(R.string.dw_2nd_bottom);
    }



}
