package com.example.nj.myapplication.DP_Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;
import com.example.nj.myapplication.R;
import com.example.nj.myapplication.Util;

public class DP_PercentOfWeek extends Activity {

    ImageView backImage,backImage2,backImage3;
    ImageView P1,P2,P3;
    Bitmap back_color,crop_image,people;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dp__percent_of_week);
        Util.setGlobalFont(this, getWindow().getDecorView());
        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int deviceWidth = displayMetrics.widthPixels;

        int deviceHeight = displayMetrics.heightPixels;

        P1 = (ImageView)findViewById(R.id.imageView_P1);
        P2 = (ImageView)findViewById(R.id.imageView_P2);
        P3 = (ImageView)findViewById(R.id.imageView_P3);

        LayoutParams params = (LayoutParams) P1.getLayoutParams();
        params.width = deviceWidth/3;

        P1.setLayoutParams(params);

        params = (LayoutParams) P2.getLayoutParams();
        params.width = deviceWidth/3;

        P2.setLayoutParams(params);

        params = (LayoutParams) P3.getLayoutParams();
        params.width = deviceWidth/3;

        P3.setLayoutParams(params);

        backImage = (ImageView)findViewById(R.id.imageView_color1);
        backImage2 = (ImageView)findViewById(R.id.imageView_color2);
        backImage3 = (ImageView)findViewById(R.id.imageView_color3);

        Log.d("P1 Width", P1.getWidth()+"");
        Log.d("P1 Height", P1.getHeight()+"");

        people = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dp_people_full));

//        people.setWidth(deviceWidth/3);
//        people.setHeight(deviceHeight/3);

        back_color = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dp_background_red));
        crop_image = cropCenterBitmap(back_color, back_color.getWidth(), back_color.getHeight() / (DPRateActivity.rates * 20));
//주석!
        backImage.setImageBitmap(crop_image);

        back_color = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dp_background_blue));
        crop_image = cropCenterBitmap(back_color, back_color.getWidth(), back_color.getHeight()/(DPRateActivity.rates*20));
        //backImage2.setImageBitmap(crop_image);

        P1.setImageBitmap(people);
        //P2.setImageBitmap(people);
        //P3.setImageBitmap(people);
     //   backImage.setImageBitmap(back_color);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dp__percent_of_week, menu);
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

    public Bitmap cropCenterBitmap(Bitmap src, int w, int h) {
        if(src == null)
            return null;

        int width = src.getWidth();
        int height = src.getHeight();

        if(width < w && height < h)
            return src;

        int x = 0;
        int y = 0;

        if(width > w)
        {
            x = (width - w)/2;
        }
        if(height > h) {
            y = (height - h)/2;
        }

        int cw = w; // crop width
        int ch = h; // crop height

        if(w > width)
            cw = width;

        if(h > height)
            ch = height;

        return Bitmap.createBitmap(src, x, y, cw, ch);
    }
}
