package com.bwie.banner;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ASUS on 2017/12/2.
 */

public class MyBanner extends LinearLayout {
    private ViewPager vp;
    private LinearLayout ll;
    private List<ImageView>imgList=new ArrayList<>();
    private Handler handler=new Handler();
    private int index=1;
    private int position;


    public MyBanner(Context context) {
        this(context,null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.banner,this);
        vp=findViewById(R.id.vp);
        ll=findViewById(R.id.ll);
    }

    public void setData(List<String> list){
        if (list==null){
            throw new RuntimeException("集合不能为空");
        }
        for (int i = 0; i <list.size() ; i++) {
            ImageView imageView=new ImageView(getContext());
            imgList.add(imageView);
            ImageLoader.getInstance().displayImage(list.get(i),imageView);

            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(),WebViewActivity.class);
                    intent.putExtra("url","https://www.baidu.com/");
                    getContext().startActivity(intent);
                }
            });

            //创建小圆点

            ImageView ivCircle=new ImageView(getContext());
            LayoutParams laoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            laoutParams.leftMargin=5;
            ivCircle.setLayoutParams(laoutParams);
            ivCircle.setBackgroundResource(R.drawable.circle_nomal);
            ll.addView(ivCircle);

        }

        ImageView iv=(ImageView)ll.getChildAt(0);
        iv.setBackgroundResource(R.drawable.circle_select);
        MyAdapter adapter = new MyAdapter();
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new MyPageChangeListener());

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        vp.setCurrentItem((++index)%imgList.size());
                    }
                });
            }
        },1000,3000);

    }

    class MyPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //重置所有的圆点装点
            reset();
            ImageView iv = (ImageView) ll.getChildAt(position);
            iv.setBackgroundResource(R.drawable.circle_select);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void reset() {
        int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView iv = (ImageView) ll.getChildAt(i);
            iv.setBackgroundResource(R.drawable.circle_nomal);
        }
    }

    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=imgList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);

        }
    }
}
