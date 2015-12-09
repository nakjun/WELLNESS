package com.example.nj.myapplication.YN_Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nj.myapplication.MainActivity;
import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

public class YN_boardActivity extends Activity {

    phpDown task;
    phpUp uptask;
    phpGet imagetask;
    String TYPE1[][] = new String[6][2];
    String TYPE2[][] = new String[6][2];
    int index1, index2;
    String board_img[];
    int img_name[] = {R.drawable.yn_actiivity_tr, R.drawable.yn_activity_movie, R.drawable.yn_actiivity_tv, R.drawable.yn_actiivity_cleaning, R.drawable.yn_activity_playing, R.drawable.yn_acitivity_game, R.drawable.yn_activity_reading, R.drawable.yn_activity_food, R.drawable.yn_activity_inst, R.drawable.yn_activity_sing, R.drawable.yn_actiivity_shopping, R.drawable.yn_actiivity_daily};
    ListView listview;
    final int REQ_CODE_SELECT_IMAGE = 100;
    ArrayList<Listviewitem> data;
    ImageView plus, databox, image;
    TextView t1, t2, t3, t4;
    EditText e1, e2, e3;
    Calendar cal;
    Button submit;
    String day="";
    String requestdata,number="";

    final String serverURL="http://220.69.209.170/psycho/";

    Bitmap bitmap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yn_board);
        Util.setGlobalFont(this, getWindow().getDecorView());
        index1 = YNActionAcitivity.index;
        index2 = YNActionAcitivity.index2;

        imagetask = new phpGet();
        imagetask.execute("http://220.69.209.170/psycho/yn_get_number.php?id="+MainActivity.LoginID.get_ID());

        cal= Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int today = cal.get(Calendar.DAY_OF_MONTH);
        day  = month +"-"+today;

        t1 = (TextView) findViewById(R.id.tview_loadimage);
        t2 = (TextView) findViewById(R.id.tview_upload_with);
        t3 = (TextView) findViewById(R.id.tview_upload_where);
        t4 = (TextView) findViewById(R.id.tview_upload_when);

        e1 = (EditText) findViewById(R.id.edit_with);
        e2 = (EditText) findViewById(R.id.edit_where);
        e3 = (EditText) findViewById(R.id.edit_when);

        submit = (Button)findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadPhoto(bitmap);
                uptask = new phpUp();
                String url="http://220.69.209.170/psycho/yn_input_board.php?id=";

                url+=MainActivity.LoginID.get_ID()+"&type=";
                url+=TYPE1[index1][index2]+"&day=";
                url+=day+"&image=";

                url+=((int)number.charAt(1)-48)+"&with=";

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();

                try {
                    s1 = URLEncoder.encode(s1,"UTF-8");
                    s2 = URLEncoder.encode(s2,"UTF-8");
                    s3 = URLEncoder.encode(s3,"UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url+=s1+"&place=";
                url+=s2+"&when=";
                url+=s3;

                Log.d("url",url);
                uptask.execute(url);

                Intent intent = new Intent(YN_boardActivity.this, YN_boardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        listview = (ListView) findViewById(R.id.listView2);
        data = new ArrayList<>();

        databox = (ImageView) findViewById(R.id.img_databox_BoardInput);
        image = (ImageView) findViewById(R.id.img_databox_image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });

        plus = (ImageView) findViewById(R.id.btn_boardplus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databox.setVisibility(View.VISIBLE);
                image.setVisibility(View.VISIBLE);
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                t4.setVisibility(View.VISIBLE);
                e1.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                e3.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
            }
        });

        init();
        search_db();

        set_korean();
        TextView tView = (TextView) findViewById(R.id.textView);
        tView.setText(TYPE2[index1][index2]);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            final Uri selectimageUri = data.getData();
            final String[] filePathColumn = {MediaStore.Images.Media.DATA};

            final Cursor imageCursor = this.getContentResolver().query(selectimageUri, filePathColumn, null, null, null);
            imageCursor.moveToFirst();

            final int coulmnindex = imageCursor.getColumnIndex(filePathColumn[0]);
            final String imagePath = imageCursor.getString(coulmnindex);
            imageCursor.close();

            bitmap = BitmapFactory.decodeFile(imagePath);
            listview.setBackgroundColor(Color.alpha(50));
            image.setImageBitmap(bitmap);

            t1.setVisibility(View.INVISIBLE);
        }
    }

    void search_db() {
        String URL = "http://220.69.209.170/psycho/yn_search_board.php?type=" + TYPE1[index1][index2];
        Log.d("URL", URL);
        task = new phpDown();
        task.execute(URL);

        ListviewAdapter adapter = new ListviewAdapter(this, R.layout.board_listview, data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("VALUE", position + "");
                //YNActionAcitivity.index = position;
            }
        });
    }


    void init() {
        TYPE1[0][0] = "TRIP";
        TYPE1[0][1] = "MOVIE";
        TYPE1[1][0] = "TV";
        TYPE1[1][1] = "CLEAN";
        TYPE1[2][0] = "PLAYING";
        TYPE1[2][1] = "GAME";
        TYPE1[3][0] = "READING";
        TYPE1[3][1] = "FOOD";
        TYPE1[4][0] = "INST";
        TYPE1[4][1] = "SING";
        TYPE1[5][0] = "SHOPPING";
        TYPE1[5][1] = "DIARY";
    }

    void set_korean() {
        TYPE2[0][0] = "여행";
        TYPE2[0][1] = "영화보기";
        TYPE2[1][0] = "예능보기";
        TYPE2[1][1] = "청소하기";
        TYPE2[2][0] = "운동하기";
        TYPE2[2][1] = "게임하기";
        TYPE2[3][0] = "독서하기";
        TYPE2[3][1] = "맛있는 음식";
        TYPE2[4][0] = "음악듣기";
        TYPE2[4][1] = "노래부르기";
        TYPE2[5][0] = "쇼핑하기";
        TYPE2[5][1] = "일기쓰기";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_yn_board, menu);
        return true;
    }
        private void uploadPhoto(final Bitmap bitmap) {

        Thread thread = new Thread(new Runnable() {

            public void run() {

                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
                byte[] ba = bao.toByteArray();
                String ba1 = Base64.encodeToString(ba, Base64.DEFAULT);
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("image", ba1));
                nameValuePairs.add(new BasicNameValuePair("name", MainActivity.LoginID.get_ID()));
                nameValuePairs.add(new BasicNameValuePair("number", (int)number.charAt(1)-48+""));
                Log.d("TEXT",nameValuePairs.get(1).getValue());
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost("http://220.69.209.170/psycho/yn_board_image_upload.php");
                    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = client.execute(post);
                    //HttpEntity entity = response.getEntity();
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        thread.start();
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

    private class phpDown extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 연결되었으면.
                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for (; ; ) {
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if (line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return jsonHtml.toString();
        }


        protected void onPostExecute(String str) {

            requestdata = str;
            board_img = requestdata.split("\n");

            Log.d("SIZE",board_img.length+"");

            for(int i=0;i<board_img.length;i++)
            {
                if(board_img[i].length()>1) {
                    //Log.d("str",board_img[i]);
                    //Log.d("length",board_img[i].length()+"");
                    String[] dat = board_img[i].split(",");
                    for (int j = 0; j < dat.length; j++) {
                        if ((int) dat[j].charAt(0) > 1000) {
                            String str2 = "";
                            for (int x = 1; x < dat[j].length(); x++) {
                                str2 += dat[j].charAt(x);
                            }
                            //Log.d("str2", str2);
                            dat[j] = str2;
                        }
                        Log.d("len", dat[j].length() + "");
                    }
                    Listviewitem line = new Listviewitem(new ImageLoader().getBitmapIMG(dat[0],dat[1]));
                    data.add(line);
                }
            }
        }

    }
    private class phpUp extends AsyncTask<String, Integer,String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                Log.d("url",urls[0]);
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
            Log.d("return string",jsonHtml.toString());
            return jsonHtml.toString();
        }


        protected void onPostExecute(String str){
            Log.d("str",str);
            databox.setVisibility(View.INVISIBLE);
            image.setVisibility(View.INVISIBLE);
            t1.setVisibility(View.INVISIBLE);
            t2.setVisibility(View.INVISIBLE);
            t3.setVisibility(View.INVISIBLE);
            t4.setVisibility(View.INVISIBLE);
            e1.setVisibility(View.INVISIBLE);
            e2.setVisibility(View.INVISIBLE);
            e3.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.INVISIBLE);
            search_db();
        }

    }


    private class phpGet extends AsyncTask<String, Integer,String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                Log.d("url",urls[0]);
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
            Log.d("return string",jsonHtml.toString());
            return jsonHtml.toString();
        }


        protected void onPostExecute(String str){
            number = str.replace("\n","");
            Log.d("NUMBER",number);
        }
    }

    class Listviewitem {
        private int icon;
        private Bitmap bitmap;

        public int getIcon() {
            return icon;
        }
        public Bitmap getbitmap() {
            return bitmap;
        }

        public Listviewitem(Bitmap bitmap)
        {
            this.bitmap = bitmap;
        }



        public Listviewitem(int icon) {
            this.icon = icon;
        }
    }

    class ListviewAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<Listviewitem> data;
        private int layout;

        public ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.data = data;
            this.layout = layout;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(layout, parent, false);
            }
            final Listviewitem listviewitem = data.get(position);
            ImageView icon = (ImageView) convertView.findViewById(R.id.img_Board);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int deviceWidth = displayMetrics.widthPixels;

            Bitmap bitmap2;
            bitmap2 = Bitmap.createScaledBitmap(listviewitem.getbitmap(), deviceWidth, 400, false);
            icon.setImageBitmap(bitmap2);
            //icon.setImageResource(listviewitem.getIcon());

            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return convertView;
        }


    }
}
