package com.example.nj.myapplication.DP_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nj.myapplication.MainActivity;
import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;
import com.example.nj.myapplication.YN_Activity.YesNoActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DpActivity extends Activity {
    ImageView img1,img2;
    Button btn1,btn2,btn3;
    phpUp task;
    Bitmap temp;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dp);
        Util.setGlobalFont(this, getWindow().getDecorView());
        ID = MainActivity.LoginID.get_ID();




        img1 = (ImageView)findViewById(R.id.imageView2);
        img2 = (ImageView)findViewById(R.id.imageView3);

        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dp_main_picture), MainActivity.width, MainActivity.height/2, false);
        img1.setImageBitmap(temp);

        ImageButton img_btn = (ImageButton)findViewById(R.id.btn_nextdp);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent i = new Intent(DpActivity.this,DPSelectActivity.class);
                startActivity(i);
                finish();
            }
        });
        btn1 = (Button)findViewById(R.id.btn_datainput);
        btn2 = (Button)findViewById(R.id.btn_month);
        btn3 = (Button)findViewById(R.id.btn_week);

        btn1.setWidth(MainActivity.width / 3);
        btn1.setHeight(MainActivity.height / 7);
        btn2.setWidth(MainActivity.width / 3);
        btn2.setHeight(MainActivity.height / 7);
        btn3.setWidth(MainActivity.width / 3);
        btn3.setHeight(MainActivity.height / 7);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new phpUp();
                task.execute("http://220.69.209.170/psycho/checkDP.php?id="+ ID);
                //task = new phpDown();
                //task.execute("http://220.69.209.170/psycho/login.php?id=" + ID + "&pw=" + PW);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DpActivity.this,CalendarView.class);
                startActivity(i);
                //finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DpActivity.this,DP_PercentOfWeek.class);
                startActivity(i);
                //finish();
            }
        });

    }
    @Override
    protected void onDestroy() {

        Destory(img1);
        Destory(img2);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dp, menu);
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

    private class phpUp extends AsyncTask<String, Integer,String> {

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


        protected void onPostExecute(String str) {
            Log.d("RETURN DATA",str);

            Log.d("REUTRN DATA SIZE",str.length()+"");

            if(str.charAt(0)=='O')
            {
                Intent i = new Intent(DpActivity.this,DPSelectActivity.class);
                startActivity(i);
                finish();
            }
            else
                Toast.makeText(getApplicationContext(), "오늘의 데이터를 이미 입력하셨습니다!", Toast.LENGTH_SHORT).show();

        }

    }

}
