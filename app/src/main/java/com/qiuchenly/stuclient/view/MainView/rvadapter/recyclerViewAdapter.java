package com.qiuchenly.stuclient.view.MainView.rvadapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.ret_news;
import com.qiuchenly.stuclient.R;
import com.qiuchenly.stuclient.view.MainView.news_interface;
import com.qiuchenly.stuclient.view.MainView.presenter.MainPresenterImp;

/**
 * Author : QiuChenLy
 * Date    : 2017/8/29
 * Func    : null
 * LastEdit: 2017/8/29
 */

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.VH> {

    String[] arr = new String[]{
            "校级公告", "学院公告", "年级提示", "活动预告", "我的消息", "热点关注"
    };
    news_interface inew = null;
    MainPresenterImp MainPresenterImp;
    ret_news ret_news;

    public recyclerViewAdapter(news_interface iNews, MainPresenterImp MainPresenterImp) {
        inew = iNews;
        this.MainPresenterImp = MainPresenterImp;
        this.MainPresenterImp.getSchoolNews();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_header, parent, false);
            inew.nav_news((ViewPager) v.findViewById(R.id.nav_SchoolNews));
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_normal_view, parent, false);
        }
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int position) {
        if (position == 0) {
            return;
        }
        h.rv_title.setText(arr[position - 1]);
    }

    @Override
    public int getItemCount() {
        return arr.length + 1;
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
}
