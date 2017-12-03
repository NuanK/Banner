package com.bwie.banner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private MyBanner myBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();

    }

    private void initView() {

        myBanner = (MyBanner) findViewById(R.id.mBanner);
    }

    private void getData() {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://120.27.23.105/ad/getAd")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final List<String> list = new ArrayList<>();
                final List<String> listurl = new ArrayList<>();

                String string = response.body().string();
                TestBean testBean = new Gson().fromJson(string, TestBean.class);
                for (int i=0;i<testBean.getData().size();i++){
                    list.add(testBean.getData().get(i).getIcon());
                    listurl.add(testBean.getData().get(i).getUrl());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myBanner.setData(list,listurl);
                    }
                });
            }
        });

    }




}
