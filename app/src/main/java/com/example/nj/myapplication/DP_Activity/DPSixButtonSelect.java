package com.example.nj.myapplication.DP_Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nj.myapplication.R;

public class DPSixButtonSelect extends Activity {
    String select[] = {"", ""};
    String Text[] = {"그 사람을 때리고 밀쳤다", "소리나 비명을 질렀다", "내버려두고 가버렸다", "어른에게 말했다", "아무것도 하지 않았다", "기타 다른 행동"};
    String Text_Title = "기분(감정)이 들때 나는 무엇을 했나요?";

    boolean flag = false;
    TextView title;
    Button btn[] = new Button[6];

    Intent intent,get_intent;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpsix_button_select);

        init();

        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    select[1] = btn[0].getText().toString();
                    intent_setting();
                } else {
                    select[0] = btn[0].getText().toString();
                    setTextSixButton();
                    flag = true;
                }
            }
        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    select[1] = btn[1].getText().toString();
                    intent_setting();
                } else {
                    select[0] = btn[1].getText().toString();
                    setTextSixButton();
                    flag = true;
                }
            }
        });
        btn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    select[1] = btn[2].getText().toString();
                    intent_setting();
                } else {
                    select[0] = btn[2].getText().toString();
                    setTextSixButton();
                    flag = true;
                }
            }
        });
        btn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    select[1] = btn[3].getText().toString();
                    intent_setting();
                } else {
                    select[0] = btn[3].getText().toString();
                    setTextSixButton();
                    flag = true;
                }
            }
        });
        btn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    select[1] = btn[4].getText().toString();
                    intent_setting();
                } else {
                    select[0] = btn[4].getText().toString();
                    setTextSixButton();
                    flag = true;
                }
            }
        });
        btn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    select[1] = btn[5].getText().toString();
                    intent_setting();
                } else {
                    select[0] = btn[5].getText().toString();
                    setTextSixButton();
                    flag = true;
                }
            }
        });
    }

    void init() {
        get_intent=getIntent();
        str = get_intent.getStringExtra("status");

        intent = new Intent(DPSixButtonSelect.this, DPmyStatus.class);
        intent.putExtra("status",str);

        title = (TextView)findViewById(R.id.dpSixButtonTitle);

        btn[0] = (Button) findViewById(R.id.BUTTON_ONE);
        btn[1] = (Button) findViewById(R.id.BUTTON_TWO);
        btn[2] = (Button) findViewById(R.id.BUTTON_THREE);
        btn[3] = (Button) findViewById(R.id.BUTTON_FOUR);
        btn[4] = (Button) findViewById(R.id.BUTTON_FIVE);
        btn[5] = (Button) findViewById(R.id.BUTTON_SIX);
    }


    void setTextSixButton() {

        for (int i = 0; i < 6; i++) {
            btn[i].setText(Text[i]);
        }
    }

    void intent_setting() {

        intent.putExtra("select1",select[0]);
        intent.putExtra("select2",select[1]);

        startActivity(intent);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dpsix_button_select, menu);
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
