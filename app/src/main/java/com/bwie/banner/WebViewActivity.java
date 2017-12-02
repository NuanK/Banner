package com.bwie.banner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView mW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        mW.loadUrl(url);
    }

    private void initView() {
        mW = (WebView) findViewById(R.id.w);
        WebSettings settings=mW.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}
