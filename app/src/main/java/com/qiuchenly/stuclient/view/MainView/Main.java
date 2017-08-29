package com.qiuchenly.stuclient.view.MainView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiuchenly.stuclient.R;
import com.qiuchenly.stuclient.view.MainView.presenter.MainPresenterlmp;

import Basic.BaseApp;
import Basic.SharedPreferences.iViewGetPreference;
import Basic.statusbar.StatusBarUtil;

/**
 * View层交互处理
 */


public class Main extends BaseApp implements iMainViews, iViewGetPreference {
    DrawerLayout lDrawerlayout;
    MainPresenterlmp mainPresenterlmp = null;
    ImageView iv_userPic, iv_HeadImage;
    TextView tuserName, tuserNick,nav_title_username;

    LinearLayout nav_program_exitUser, nav_program_movetoback;
    FrameLayout ll_title_menu;


    @Override
    public void loadComplete() {
        //侧滑菜单透明
        lDrawerlayout = (DrawerLayout) findViewById(R.id.lDrawerlayout);
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, lDrawerlayout,
                Color.argb(0, 0, 0, 0));
        mainPresenterlmp = new MainPresenterlmp(this, this);

    }

    @Override
    public void SetOnClickListener() {
        tuserName = $(R.id.nav_user_name, false);
        tuserNick = $(R.id.nav_user_info, false);
        iv_userPic = $(R.id.iv_title_userImage, false);
        iv_HeadImage = $(R.id.nav_HeaderImage, false);
        //Test Model,will remove in under
        nav_title_username=$(R.id.nav_title_username,false);
        //Test Model End

        nav_program_exitUser = $(R.id.nav_program_exitUser, true);
        nav_program_movetoback = $(R.id.nav_program_exit, true);
        ll_title_menu = $(R.id.ll_title_menu, true);
    }

    @Override
    public void BeClick(View v) {
        switch (v.getId()) {
            case R.id.nav_program_exitUser:
                mainPresenterlmp.exitUser();
                break;
            case R.id.ll_title_menu:
                lDrawerlayout.openDrawer(Gravity.START);
                break;
        }

    }

    @Override
    public int SetThisContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void getNews() {

    }

    @Override
    public void getImage(Bitmap bitmap) {
        iv_userPic.setImageBitmap(bitmap);
    }

    @Override
    public void getName(String name) {
        tuserName.setText(name);
        nav_title_username.setText(name);
    }

    @Override
    public void getnick(String nick) {
        tuserNick.setText(nick);
    }

    @Override
    public void exitUser() {
        lDrawerlayout.closeDrawer(Gravity.START);
    }

    @Override
    public Context getInstance() {
        return this;
    }
}
