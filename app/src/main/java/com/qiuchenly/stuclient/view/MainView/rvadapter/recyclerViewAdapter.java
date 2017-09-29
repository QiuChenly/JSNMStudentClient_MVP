package com.qiuchenly.stuclient.view.MainView.rvadapter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.newsInfo;
import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.ret_news;
import com.qiuchenly.stuclient.Basic.httpClient.httpClient;
import com.qiuchenly.stuclient.R;
import com.qiuchenly.stuclient.view.MainView.news_interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : QiuChenLy
 * Date    : 2017/8/29
 * Func    : null
 * LastEdit: 2017/8/29
 */

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.VH> {
    news_interface inew = null;
    List<newsInfo> ret_news;
    Handler handler;

    public recyclerViewAdapter(news_interface iNews, newsInfo ret_news) {
        inew = iNews;
        this.ret_news=new ArrayList<>();
        this.ret_news.add(ret_news);
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_header, parent, false);
            inew.nav_news((ViewPager) v.findViewById(R.id.nav_SchoolNews), ret_news);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_normal_view, parent, false);
        }
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(final VH h, int position) {
        if (position == 0) {
            return;
        }
        if (ret_news.get(position + 2).imgNameList.length >= 1) {
            h.rv_image.setVisibility(View.VISIBLE);
            final String url = ret_news.get(position + 2).imgNameList[0].replace("http://lantuservice.com/mobilecampus/", "");
            new Thread() {
                @Override
                public void run() {
                    Bitmap bit = null;
                    try {
                        bit = httpClient.Request_Image("http://lantuservice.com/mobilecampus/" + url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    final Bitmap finalBit = bit;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            h.rv_image.setImageBitmap(finalBit);
                        }
                    });
                }
            }.start();
        }else{
            h.rv_image.setVisibility(View.GONE);
        }
        h.smallTitle.setText(ret_news.get(position + 2).realName);
        h.rv_title.setText(ret_news.get(position + 2).title);
    }

    @Override
    public int getItemCount() {
        return ret_news.size() - 2;
    }

    class VH extends RecyclerView.ViewHolder {
        TextView rv_title, smallTitle;
        ImageView rv_image;

        public VH(View i) {
            super(i);
            rv_title = (TextView) i.findViewById(R.id.news_normal_title);
            smallTitle = (TextView) i.findViewById(R.id.news_normal_smallTitle);
            rv_image = (ImageView) i.findViewById(R.id.news_normal_image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public void addmore(){

    }
}
