package com.example.my.practice_bbb7;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1,btn2;
    private ImageView imageView;

    private Retrofit retrofit;

    private MyretrofitApi myretrofitApi;
    private ByteArrayOutputStream byteArrayOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        retrofit=new Retrofit.Builder().baseUrl("").build();
        myretrofitApi=retrofit.create(MyretrofitApi.class);
        btn1= (Button) findViewById(R.id.button01);
        btn2= (Button) findViewById(R.id.button02);
        imageView= (ImageView) findViewById(R.id.imageview);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button01:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Call call=myretrofitApi.downLoad("http://img3.imgtn.bdimg.com/it/u=3843979064,1483327667&fm=21&gp=0.jpg");
                        try {
                            Response<ResponseBody>response=call.execute();
                            InputStream inputStream=response.body().byteStream();
                            byteArrayOutputStream=new ByteArrayOutputStream();
//                            File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/ff.jpg");
//                            file.createNewFile();
//                            FileOutputStream fileOutputStream=new FileOutputStream(file);
                            byte[]bytes=new byte[1024];
                            int len=inputStream.read(bytes);
                            while (len!=-1){
                                byteArrayOutputStream.write(bytes,0,len);
                                inputStream.read(bytes);
                            }

                            byte[]bytes1=byteArrayOutputStream.toByteArray();
                            final Bitmap bitmap= BitmapFactory.decodeByteArray(bytes1,0,bytes1.length);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                            inputStream.close();

                            byteArrayOutputStream.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
            case R.id.button02:


                break;
        }

    }
}
