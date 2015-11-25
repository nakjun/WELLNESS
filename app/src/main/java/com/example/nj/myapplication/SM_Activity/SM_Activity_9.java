package com.example.nj.myapplication.SM_Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

public class SM_Activity_9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm_activity_9);
        Util.setGlobalFont(this, getWindow().getDecorView());
        Toolbar toolbar = (Toolbar) findViewById(R.id.sm_9_toolbar);
        setSupportActionBar(toolbar);


    }

}
