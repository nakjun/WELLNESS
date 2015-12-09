package com.example.nj.myapplication.YN_Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by NJ on 2015-11-30.
 */
public class ImageLoader {

    public ImageLoader()
    {
        new ThreadPolicy();
    }


    public Bitmap getBitmapIMG(String FOLDER, String FILE)
    {
        Bitmap bitmapImg = null;
        String url_temp="http://220.69.209.170/psycho/";

        try {
            try {
                //FOLDER = URLEncoder.encode(FOLDER,"UTF-8");
                FILE = URLEncoder.encode(FILE,"UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            url_temp+=FOLDER+"/"+FILE+".jpg";
            Log.d("url",url_temp);
            URL url = new URL(url_temp);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = null;
            try {
                is = conn.getInputStream();
                bitmapImg = BitmapFactory.decodeStream(is);
            }catch(IOException e)
            {
                e.printStackTrace();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

        return bitmapImg;
    }
}
