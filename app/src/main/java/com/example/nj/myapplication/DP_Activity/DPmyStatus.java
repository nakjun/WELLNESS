package com.example.nj.myapplication.DP_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nj.myapplication.HubActivity;
import com.example.nj.myapplication.IDSingletonclass;
import com.example.nj.myapplication.MainActivity;
import com.example.nj.myapplication.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DPmyStatus extends Activity {
    Intent intent,get_intent ;
    String result1,result2;

    IDSingletonclass LoginID;
    String get_ID;
    String get_User_name;
    int status;
    int image_id[]={R.drawable.dp_status_anger,R.drawable.dp_status_frustration,R.drawable.dp_status_regret,R.drawable.dp_status_appointment,R.drawable.dp_status_sad};

    TextView text_status;
    TextView description;

    phpDown task;

    String Status_Name[]={"\n분노\n","\n좌절\n","\n후회\n","\n야속\n","\n슬픔\n","\n기타\n"};
    String Status_rates[]={"아주 낮음(1점)","낮음(2점)","보통(3점)","높음(4점)","아주 높음(5점)"};

    String Text[]=new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpmy_status);

        intent_process();

        Log.d("status",DPSelectActivity.status+"");

        init();

        task = new phpDown();
        task.execute("http://220.69.209.170/psycho/search.php?id=" + get_ID);
    }

    void setting_description_text()
    {
        Text[0] = "오늘 많이 힘든 날이었군요. 때때로 누구나 자신의 감정을 감당하기 힘들 때가 있답니다. 누군가에게 이런 나의 마음을 표현해 보는 것이 큰 도움이 되기도 해요.\n" +
                "답답하고 힘든 마음을 충분히 누군가에게 털어 놓고 나면, 한결 기분이 나아진답니다.\n" +
                "부모님, 친구, 선생님 혹은 사이버상담실로 언제든 도움을 요청해보세요."+get_User_name+"님이 좀 더 힘을 낼 수 있고, 편안한 마음을 가지기를 바랄께요. 화이팅~!";
        Text[1] = "좌절감이 높은 날이네요. 많이 힘든 하루가 되었겠어요~\n"+
            "아무것도 할 수 없을 것 같은 지금 생각은 지금의 생각일 뿐이에요!\n"+
            "내일은 또 다른 상황이… 미래에는 지금보다 더 좋고 멋진 일이 벌어질 거라는 믿음을 가져보아요. \n그 속에서 새로운 나를 발견하고 발전해나간다는 걸 잊지 말고요!\n"+
            "늪에 빠지면 온몸의 힘을 빼고 시간을 버는 게 우선이란 거 우리 알고 있지요? 늪에서 오래 살아남는 게 중요하니까요!"+
            "지금의 좌절의 늪.. 늪에서는 허우적거리면 거릴수록 수렁 속으로 빠져들게 되요."+
            "두렵고 불안하지만, \n오늘 하루를 묵묵히 살아내는 것이 중요해요! 힘을 내요. 파이팅!!";
    }


    void init(){
        //status = DPSelectActivity.status;

        get_ID = MainActivity.LoginID.get_ID();
        Toast.makeText(DPmyStatus.this, get_ID, Toast.LENGTH_SHORT).show();

        text_status = (TextView)findViewById(R.id.TextView_Status);
        description = (TextView)findViewById(R.id.textView_DP_DESCRIPTION);

        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(DPmyStatus.this,DP_PercentOfWeek.class));
                //finish();
            }
        });

    }

    void set_display()
    {
        text_status.setBackgroundResource(image_id[DPSelectActivity.status]);
        text_status.setText(Status_Name[DPSelectActivity.status] + Status_rates[DPRateActivity.rates]);

        description.setText(Text[DPSelectActivity.status]);
    }


    void intent_process()
    {
        get_intent = getIntent();
        result1 = get_intent.getStringExtra("select1");
        result2 = get_intent.getStringExtra("select2");

        //Toast.makeText(DPmyStatus.this, result1, Toast.LENGTH_SHORT).show();
        //Toast.makeText(DPmyStatus.this, result2, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dpmy_status, menu);
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

    private class phpDown extends AsyncTask<String, Integer,String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                // 연결되었으면.
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
            return jsonHtml.toString();
        }


        protected void onPostExecute(String str){

            Log.d("userName",str);

            if(str.equals("n"))
            {

            }
            else
            {
                get_User_name = str;
                setting_description_text();
                set_display();
            }
        }

    }
}


