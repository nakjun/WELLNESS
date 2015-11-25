package com.example.nj.myapplication.CDI_Activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

public class CDI_Activity1 extends Activity {

    RadioButton R1,R2,R3;
    RadioGroup RGroup;

    ImageButton btn;

    String []TEXT1={"나는 가끔 슬프다","나는 모든 일이 잘 되어 갈 것 같다","나는 무슨 일이든지 웬만큼 한다","나는 재미있는 일들이 많다","나는 가끔 못됐다","나는 가끔 나에게 나쁜 일이 일어나지 않을까 생각한다","나는 나 자신을 좋아한다","잘못 되는 일은 보통 내 탓이 아니다","나는 자살을 생각하지 않는다","나는 때때로 울고 싶은 기분이 든다","이 일 저 일로 해서 짜증이 날 때가 조금 있다","나는 사람들과 함께 있는 것이 좋다","나는 어떤 일에 대해 쉽게 결정을 내린다","나는 괜찮게 생겼다","나는 별로 어렵지 않게 학교 공부를 해낼 수 있다","나는 잠을 잘 잔다","나는 가끔 피곤하다","나는 밥맛이 좋다","나는 몸이 아파도 걱정하지 않는다","나는 외롭지 않다","나는 학교생활이 많이 재미있다","나는 친구가 많다","나는 학교 성적이 괜찮다","나는 다른 아이들처럼 착하다","나를 진심으로 좋아하는 사람이 분명히 있다","누가 나에게 시킨 일은 아주 잘한다","나는 사람들과 사이좋게 잘 지낸다"};
    String []TEXT2={"나는 자주 슬프다","나는 모든 일이 잘 될지 확신할 수 없다","나는 잘하지 못하는 일이 없다","나는 재미있는 일들이 조금 있다","나는 못됐을 때가 많다","나는 나에게 나쁜 일이 일어날까 걱정한다","나는 나 자신을 좋아하지 않는다","잘못 되는 일 중 내 탓인 것이 많다","나는 자살에 대하여 생각은 하지만, 자살하지는 않을 것이다","나는 울고 싶은 기분인 날이 많다","이 일 저 일로 해서 짜증이 날 때가 많다","나는 사람들과 함께 있는 것이 싫을 때가 있다","나는 어떤 일에 대해 결정을 내리기가 어렵다","나는 못생긴 구석이 약간 있다","나는 학교 공부를 해내려면 많이 노력해야만 한다","나는 잠 들기 어려운 때가 많다","나는 자주 피곤하다","나는 가끔 밥맛이 없다","나는 몸이 아픈 것에 대해 가끔 걱정한다","나는 가끔 외롭다","나는 학교생활이 가끔 재미있다","나는 친구가 조금 있지만 더 있었으면 한다","나는 학교 성적이 예전처럼 좋지 않다","나는 착하지 않은 편이다","나를 진심으로 좋아하는 사람이 있을지도 모른다","누가 나에게 어떤 일을 시키면 하지 않는 편이다","나는 사람들과 자주 싸운다"};
    String []TEXT3={"나는 항상 슬프다","나는 모든 일이 잘 되어가지 않는다","나는 모든 일을 잘하지 못한다","나는 재미있는 일들이 하나도 없다","나는 언제나 못됐다","나는 나에게 무서운 일이 일어나리라는 것을 확신한다","나는 나 자신을 미워한다","잘못 되는 일은 모두 내 탓이다","나는 자살하고 싶다","나는 매일 울고 싶은 기분이다","이 일 저 일로 해서 항상 짜증이 난다","나는 사람들과 함께 있는 것을 아주 싫어한다","나는 어떤 일에 대해 결정을 내릴 수가 없다","나는 못생겼다","나는 학교 공부를 해내려면 항상 노력해야만 한다","나는 매일 밤 잠들기가 어렵다","나는 언제나 피곤하다","나는 항상 밥맛이 없다","나는 몸이 아프면 항상 걱정한다","나는 항상 외롭다","나는 학교 생활이 재미없다","나는 친구가 하나도 없다","내가 잘하던 과목의 성적이 요즘 뚝 떨어졌다","나는 절대로 다른 아이들처럼 착할 수가 없다","나를 진심으로 좋아하는 사람은 아무도 없다","누가 나에게 어떤 일을 시키면 절대로 하지 않는다","나는 사람들과 항상 싸운다"};

    public int COUNT1=0,COUNT2=0,COUNT3=0;

    boolean FLAG = false;

    int INDEX=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdi_1);
        Util.setGlobalFont(this, getWindow().getDecorView());
        Log.d("LENGTH", TEXT1.length + "");

        R1 = (RadioButton)findViewById(R.id.option1);
        R2 = (RadioButton)findViewById(R.id.option2);
        R3 = (RadioButton)findViewById(R.id.option3);

        R1.setText(TEXT1[INDEX]);
        R2.setText(TEXT2[INDEX]);
        R3.setText(TEXT3[INDEX]);

        btn = (ImageButton)findViewById(R.id.cdi_next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(INDEX==26)
                {
                    FLAG = true;
                    Log.d("COUNT1",COUNT1+"");
                    Log.d("COUNT2",COUNT2+"");
                    Log.d("COUNT3",COUNT3+"");

                    Intent i = new Intent(CDI_Activity1.this,CDI_Activity2.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    INDEX++;
                    R1.setText(TEXT1[INDEX]);
                    R2.setText(TEXT2[INDEX]);
                    R3.setText(TEXT3[INDEX]);

                    if(R1.isChecked())
                    {
                        COUNT1++;
                    }
                    else if(R2.isChecked())
                    {
                        COUNT2++;
                    }
                    else if(R3.isChecked())
                    {
                        COUNT3++;
                    }
                }

            }
        });

        //R1.setOnClickListener(option);
        RGroup = (RadioGroup)findViewById(R.id.radioGroup1);

        RGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId)
                {
                    case R.id.option1:
                        Log.d("1번클릭",R1.getText().toString());
                        break;
                    case R.id.option2:
                        Log.d("2번클릭",R2.getText().toString());
                        break;
                    case R.id.option3:
                        Log.d("3번클릭",R3.getText().toString());
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cdi__activity1, menu);
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
