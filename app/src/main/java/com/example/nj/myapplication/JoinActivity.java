package com.example.nj.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JoinActivity extends Activity {
    DatePicker datePicker;
    EditText edit[];
    String ID, PW, NAME, GENDER, NICKNAME, ADDRESS, BIRTH;
    Button btn_submit;
    ImageView imView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        EditText ed = (EditText)findViewById(R.id.editText_inputID);
        Log.d("test",ed.getText().toString());

        btn_submit = (Button)findViewById(R.id.btn_submit_joindata);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_edit();

                Log.d("ID",ID);
//                Log.d("PW",PW);
         //       Log.d("NAME",NAME);
          //      Log.d("GENDER",GENDER);
           //     Log.d("NICKNAME",NICKNAME);
            //    Log.d("BIRTH",BIRTH);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_join, menu);
        return true;
    }

    void get_addr()
    {
        //ADDRESS =
    }



    void get_date()
    {
        datePicker = (DatePicker) findViewById(R.id.datePicker);


        datePicker.init(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        BIRTH = String.format("%4d %02d %02d", year, monthOfYear + 1, dayOfMonth);
                       // Log.d("date", BIRTH);
                        //Toast.makeText(JoinActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    void get_edit()
    {
        EditText ed = (EditText)findViewById(R.id.editText_inputID);

        ID = ed.getText().toString();
        /*PW = edit[1].getText().toString();
        NAME = edit[2].getText().toString();
        GENDER = edit[3].getText().toString();
        NICKNAME = edit[4].getText().toString();*/
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

    private class phpDown extends AsyncTask<String, Integer,String>{

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
            //txtView.setText(str);
        }

    }


}
