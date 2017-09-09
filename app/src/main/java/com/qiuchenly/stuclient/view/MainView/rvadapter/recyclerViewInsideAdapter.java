package com.qiuchenly.stuclient.view.MainView.rvadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by QiuChenly on 2017/9/8.
 */

public class recyclerViewInsideAdapter extends RecyclerView.Adapter<recyclerViewInsideAdapter.InsideVH> {
    @Override
    public InsideVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(InsideVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class InsideVH extends RecyclerView.ViewHolder{
        public InsideVH (View itemView) {
            super(itemView);
        }
    }
}
