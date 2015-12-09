package com.example.nj.myapplication.CDI_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nj.myapplication.R;

import org.w3c.dom.Text;

public class CDI_Activity2 extends Activity {

    Button btn;
    TextView Tview;
    String res[] = {"우울하지 않은 상태예요","많이 힘드시죠? \n함께 이야기 해볼까요?","당신의 마음을 알아주는 \n 사람이 없다고 느껴지나요?"};
    int index=0;
    int result=0;
    String r="";
    ImageView img_result;

    int deviceWidth,deviceHeight;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdi_2);

        img_result = (ImageView)findViewById(R.id.cdi_result);

        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        deviceWidth = displayMetrics.widthPixels;
        deviceHeight = displayMetrics.heightPixels;



        //RelativeLayout layout1 = (RelativeLayout)findViewById(R.id.cdi_layout);
        //RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)layout1.getLayoutParams();
        //params.bottomMargin = deviceHeight/15;
        //layout1.setLayoutParams(params);
        //img_result.set

        Intent i = getIntent();
        int temp = i.getIntExtra("COUNT1",0);
        result = temp * 1;
        temp = i.getIntExtra("COUNT2",0);
        result += temp * 2;
        temp = i.getIntExtra("COUNT3",0);
        result += temp * 3;

        if(result>=28) index=2;
        else if(result>=26) index=1;
        else index=0;

        String t = "총점\n"+result+"점\n";

        r = t + res[index];

        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.cdi_result),(deviceWidth*2)/3, deviceHeight/8, false);
        img_result.setImageBitmap(bitmap);



        img_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tview = (TextView)findViewById(R.id.Tview_result);
                Tview.setText(r);
                Tview.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cdi__activity3, menu);
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
