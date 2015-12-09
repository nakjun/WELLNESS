package com.example.nj.myapplication.SM_Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

public class SM_Activity_4 extends AppCompatActivity {
    ImageView cen_char,bubble;
    ImageButton back,next;
    EditText textfield;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm_activity_4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sm_4_toolbar);
        Util.setGlobalFont(this, getWindow().getDecorView());
        setTitle("");
        setSupportActionBar(toolbar);

        cen_char=(ImageView)findViewById(R.id.sm_4_main_char);
        cen_char.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_4_simpson), 500, 500, false));
        back=(ImageButton)findViewById(R.id.sm_4_back);
        back.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_btn_left), 80, 80, false));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        next=(ImageButton)findViewById(R.id.sm_4_nextbtn);
        next.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_btn_right), 80, 80, false));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: DB Send message;
                startActivity(new Intent(SM_Activity_4.this, SM_Activity_5.class));
            }
        });


        bubble=(ImageView)findViewById(R.id.sm_4_bubble);
        bubble.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sm_4_bubble), 800, 800, false));
        textfield=(EditText)findViewById(R.id.sm_4_textfield);
        textfield.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        //imm.showSoftInput(textfield,0);
        imm.showSoftInputFromInputMethod (textfield.getApplicationWindowToken(),InputMethodManager.SHOW_FORCED);

        textfield.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()>200){
                    editable.delete(200,editable.toString().length());
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        Destory(cen_char);
        Destory(back);
        Destory(bubble);
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

}
