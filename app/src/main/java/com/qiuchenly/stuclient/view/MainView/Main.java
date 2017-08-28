package com.qiuchenly.stuclient.view.MainView;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.qiuchenly.stuclient.R;
import com.qiuchenly.stuclient.view.MainView.iMainViews;
import com.qiuchenly.stuclient.view.MainView.presenter.MainPresenterlmp;

import Basic.BaseApp;
import Basic.statusbar.StatusBarUtil;

public class Main extends BaseApp implements iMainViews {
    DrawerLayout lDrawerlayout;
    MainPresenterlmp mainPresenterlmp = null;

    @Override
    public void loadComplete() {
        //侧滑菜单透明
        lDrawerlayout = (DrawerLayout) findViewById(R.id.lDrawerlayout);
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, lDrawerlayout,
                Color.argb(0, 0, 0, 0));
        mainPresenterlmp = new MainPresenterlmp(this);
    }

    @Override
    public void SetOnClickListener() {

    }

    @Override
    public void BeClick(View v) {

    }

    @Override
    public int SetThisContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void getNews() {

    }
}
