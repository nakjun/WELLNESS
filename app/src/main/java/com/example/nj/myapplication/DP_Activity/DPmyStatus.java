package com.example.nj.myapplication.DP_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nj.myapplication.R;

public class DPmyStatus extends Activity {
    Intent intent,get_intent ;
    String result1,result2;

    int image_id[]={R.drawable.dp_status_anger,R.drawable.dp_status_frustration};

    ImageView img_status;
    String str;
    String Text[]={""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpmy_status);

        img_status = (ImageView)findViewById(R.id.ImageView_Status);

        get_intent = getIntent();
        result1 = get_intent.getStringExtra("select1");
        result2 = get_intent.getStringExtra("select2");
        str = get_intent.getStringExtra("status");

        Toast.makeText(DPmyStatus.this, result1, Toast.LENGTH_SHORT).show();
        Toast.makeText(DPmyStatus.this, result2, Toast.LENGTH_SHORT).show();

        if(str.equals("0")) img_status.setImageResource(image_id[0]);
        else img_status.setImageResource(image_id[1]);
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
}
