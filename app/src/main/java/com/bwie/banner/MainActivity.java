package com.bwie.banner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyBanner myBanner;
    private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        list.add("http://img.zcool.cn/community/017274582000cea84a0e282b576a32.jpg");
        list.add("http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg");
        list.add("http://img.zcool.cn/community/017274582000cea84a0e282b576a32.jpg");
        list.add("http://img.zcool.cn/community/014a52554064690000005b03d35d4e.jpg@900w_1l_2o_100sh.jpg");
        myBanner.setData(list);

    }

    private void initView() {
        myBanner = (MyBanner) findViewById(R.id.mBanner);
    }

}
