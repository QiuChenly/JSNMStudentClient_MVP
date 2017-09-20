package com.qiuchenly.stuclient.view.MainView.BaseAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.ret_news;
import com.qiuchenly.stuclient.Basic.httpClient.httpClient;
import com.qiuchenly.stuclient.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QiuChen on 2017/9/16.
 */

public class ViewPagerAdapter extends PagerAdapter {

    ret_news data;
    List<View> list;
    final Handler handler;

    public ViewPagerAdapter(final Context context, final ret_news data) {
        this.data = data;
        list = new ArrayList<>();
        handler = new Handler(Looper.getMainLooper());
        for (int a = 0; a < 3; a++) {
            View frameLayout = LayoutInflater.from(context).inflate(R.layout.news_nav_title, null);
            TextView t = (TextView) frameLayout.findViewById(R.id.news_shortInfo);
            t.setText(data.newsList[a].title);
            final ImageView img = (ImageView) frameLayout.findViewById(R.id.news_image);
//            if(!(data.newsList[a].imgNameList.length>0)){
//                list.add(frameLayout);
//                continue;
//            }
            //TODO:暂时修复数据适配问题
            while(!(data.newsList[a].imgNameList.length>0)){
                a++;
            }
            final String url = data.newsList[a].imgNameList[0].replace("http://lantuservice.com/mobilecampus/","");
            new Thread() {
                @Override
                public void run() {
                    Bitmap bit = null;
                    try {
                        bit = httpClient.Request_Image("http://lantuservice.com/mobilecampus/"+url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    final Bitmap finalBit = bit;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            img.setImageBitmap(finalBit);
                        }
                    });
                }
            }.start();
            list.add(frameLayout);
        }
    }

    @Override
    public int getCount() {
        //首页上方滚动动画共计3个
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = list.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
