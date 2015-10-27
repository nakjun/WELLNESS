package com.example.nj.myapplication.DP_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
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
    int image_id[]={R.drawable.dp_status_anger,R.drawable.dp_status_frustration};

    ImageView img_status;
    String str;

    String Text[]={""};

    phpDown task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpmy_status);

        intent_process();
        init();

        task = new phpDown();
        task.execute("http://220.69.209.170/psycho/search.php?id=" + get_ID);


    }

    void init(){
        status = DPSelectActivity.status;

        get_ID = MainActivity.LoginID.get_ID();
        Toast.makeText(DPmyStatus.this, get_ID, Toast.LENGTH_SHORT).show();

        img_status = (ImageView)findViewById(R.id.ImageView_Status);

        img_status.setImageResource(image_id[status]);
    }


    void intent_process()
    {
        get_intent = getIntent();
        result1 = get_intent.getStringExtra("select1");
        result2 = get_intent.getStringExtra("select2");
        str = get_intent.getStringExtra("status");

        Toast.makeText(DPmyStatus.this, result1, Toast.LENGTH_SHORT).show();
        Toast.makeText(DPmyStatus.this, result2, Toast.LENGTH_SHORT).show();
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

            if(str.equals("n"))
            {

            }
            else
            {
                get_User_name = str;
            }
        }

    }
}


