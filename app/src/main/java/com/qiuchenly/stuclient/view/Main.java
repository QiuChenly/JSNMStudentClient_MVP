package com.qiuchenly.stuclient.view;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qiuchenly.stuclient.R;

import Basic.BaseApp;
import Basic.statusbar.StatusBarUtil;

public class Main extends BaseApp {
DrawerLayout lDrawerlayout;
    @Override
    public void loadComplete() {
        //侧滑菜单透明
        lDrawerlayout = (DrawerLayout) findViewById(R.id.lDrawerlayout);
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, lDrawerlayout,
                Color.argb(0, 0, 0, 0));

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
}
