package com.qiuchenly.stuclient.view.MainView.rvadapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by qiuchenly on 2017/9/28.
 */

public class recyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    recyclerViewLoadMore more;
    LinearLayoutManager LinearLayoutManager;

    public recyclerViewOnScrollListener(LinearLayoutManager linearLayoutManager, recyclerViewLoadMore recyclerViewLoadMore) {
        more = recyclerViewLoadMore;
        LinearLayoutManager = linearLayoutManager;
    }
    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = LinearLayoutManager.getItemCount();
        firstVisibleItem = LinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading
                && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            currentPage++;
            loading = true;
        }
        more.loadMoreData();
    }
}
