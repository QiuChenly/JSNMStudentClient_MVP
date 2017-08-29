package com.qiuchenly.stuclient.view.MainView.rvadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiuchenly.stuclient.R;

/**
 * Author : QiuChenLy
 * Date    : 2017/8/29
 * Func    : null
 * LastEdit: 2017/8/29
 */

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.VH> {

    String[] arr=new String[]{
        "校级公告","学院公告","年级提示","活动预告","我的消息","热点关注"
    };

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_school,parent,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int position) {
        h.rv_showTitle.setText(arr[position]);
    }

    @Override
    public int getItemCount() {
        return arr.length;
    }

    class VH extends RecyclerView.ViewHolder{
        TextView rv_showTitle;
        RecyclerView rv_showData;
        public VH(View i) {
            super(i);
            rv_showTitle= (TextView) i.findViewById(R.id.rv_showTitle);
            rv_showData= (RecyclerView) i.findViewById(R.id.rv_showData);
        }
    }
}
