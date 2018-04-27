package com.example.administrator.myapplication;
/*使用AsyncTask下载图片
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import java.io.InputStream;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private static String URL="http://images.bokee.com/artpic_upload/o/c/e/oceantokyo/40641472545482.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.image);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        new MyasynvTask().execute(URL);
    }

    class MyasynvTask extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url=params[0];
            Bitmap bitmap=null;
            URLConnection connection;
            InputStream is;
            try {
                connection=new URL(url).openConnection();//获取连接对象
                is= connection.getInputStream();//获取输入流
                BufferedInputStream bf=new BufferedInputStream(is);//包装成buffer
                bitmap= BitmapFactory.decodeStream(bf);
                is.close();
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;

        }
    }
}
