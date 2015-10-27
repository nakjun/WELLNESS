package com.example.nj.myapplication.SM_Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nj.myapplication.R;

public class SM_Activity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm__activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sm_2_toolbar);
        toolbar.setTitle("\t\t\t\t\t\t\t\t\t\t\t\tWELLNESS");
        toolbar.setTitleTextColor(R.color.sm_2_title);

        setSupportActionBar(toolbar);

    }

}
