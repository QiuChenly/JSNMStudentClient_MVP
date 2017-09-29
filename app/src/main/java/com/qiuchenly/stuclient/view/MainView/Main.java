package com.qiuchenly.stuclient.view.MainView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.loginRes;
import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.ret_news;
import com.qiuchenly.stuclient.Basic.BaseApp;
import com.qiuchenly.stuclient.Basic.SharedPreferences.iViewGetPreference;
import com.qiuchenly.stuclient.Basic.statusbar.StatusBarUtil;
import com.qiuchenly.stuclient.R;
import com.qiuchenly.stuclient.view.LoginActivity;
import com.qiuchenly.stuclient.view.MainView.BaseAdapter.PagerAnimation;
import com.qiuchenly.stuclient.view.MainView.BaseAdapter.ViewPagerAdapter;
import com.qiuchenly.stuclient.view.MainView.presenter.MainPresenterImp;
import com.qiuchenly.stuclient.view.MainView.rvadapter.recyclerViewAdapter;
import com.qiuchenly.stuclient.view.MainView.rvadapter.recyclerViewLoadMore;
import com.qiuchenly.stuclient.view.MainView.rvadapter.recyclerViewOnScrollListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * View层交互处理
 */


public class Main extends BaseApp implements iMainViews, iViewGetPreference, news_interface,recyclerViewLoadMore {

    private static final int SCHEDULETIME = 5000;
    private static final int SWITCHPICTURE = 0;
    DrawerLayout lDrawerlayout;
    public MainPresenterImp mainPresenterlmp = null;
    ImageView iv_userPic, iv_HeadImage;
    TextView tUserName, tUserNick, nav_title_username, nav_LevelTAG;

    LinearLayout nav_program_exitUser, nav_program_movetoback;
    FrameLayout ll_title_menu;

    RecyclerView rv_showNews;

    ViewPager nav_SchoolNews;

    @Override
    public void loadComplete() {
        //侧滑菜单Header透明
        lDrawerlayout = (DrawerLayout) findViewById(R.id.lDrawerlayout);
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, lDrawerlayout,
                Color.argb(0, 0, 0, 0));
        mainPresenterlmp = new MainPresenterImp(this, this);
        mainPresenterlmp.getSchoolNews();
    }

    @Override
    public void SetOnClickListener() {
        tUserName = $(R.id.nav_user_name, false);
        tUserNick = $(R.id.nav_user_info, false);
        iv_userPic = $(R.id.iv_title_userImage, false);
        iv_HeadImage = $(R.id.nav_HeaderImage, false);
        //Test Model,will remove in under
        nav_title_username = $(R.id.nav_title_username, false);
        //Test Model End

        nav_program_exitUser = $(R.id.nav_program_exitUser, true);
        nav_program_movetoback = $(R.id.nav_program_exit, true);
        ll_title_menu = $(R.id.ll_title_menu, true);
        rv_showNews = $(R.id.rv_showNews, false);
        nav_LevelTAG = $(R.id.nav_LevelTAG, false);
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
            case R.id.nav_program_exit:
                lDrawerlayout.closeDrawer(Gravity.START);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                }, 300);
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
        tUserName.setText(name);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getnick(String nick, loginRes ret) {
        tUserNick.setText(nick + " | " + ret.userLoginInfo.parentUserName);
        nav_LevelTAG.setText("Lv " + ret.userBaseInfo.vipLevelName);
        nav_title_username.setText(nick);

    }

    @Override
    public void exitUser() {
        lDrawerlayout.closeDrawer(Gravity.START);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginActivity.class, true);
            }
        }, 300);
    }


    private int nowNum = 0;
    Timer timer;
    recyclerViewAdapter recyclerViewAdapter;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void getSchoolNews(ret_news ret) {
        recyclerViewAdapter = new recyclerViewAdapter(this, ret);
        rv_showNews.setHasFixedSize(false);
        rv_showNews.setLayoutManager(new LinearLayoutManager(this));
        rv_showNews.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 5;
            }
        });
        rv_showNews.setAdapter(recyclerViewAdapter);
        rv_showNews.setOnScrollChangeListener(
                (View.OnScrollChangeListener) new recyclerViewOnScrollListener((LinearLayoutManager) rv_showNews.getLayoutManager(),
                        this));

    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SWITCHPICTURE:
                    if (msg.arg1 != 0) {
                        nav_SchoolNews.setCurrentItem(msg.arg1);
                    } else {
                        nav_SchoolNews.setCurrentItem(0, false);
                    }
                    break;
            }
        }
    };

    @Override
    public Context getInstance() {
        return this;
    }


    //接口数据
    @Override
    public void nav_news(ViewPager v, ret_news ret) {
        //ret 数据返回 适配
        nav_SchoolNews = v;// $(R.id.nav_SchoolNews, false);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, ret);
        nav_SchoolNews.setPageTransformer(true, new PagerAnimation());
        nav_SchoolNews.setAdapter(adapter);
        //设置Viewpager缓存数量为 3 页,防止内存冲突导致页面被回收
        nav_SchoolNews.setOffscreenPageLimit(3);
        if (timer == null) {
            timer = new Timer();
        } else {
            timer.cancel();
        }

        //TimeTask定时任务,间隔
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = SWITCHPICTURE;
                if (nowNum == 2) {
                    nowNum = 0;
                } else {
                    nowNum += 1;
                }
                msg.arg1 = nowNum;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(task, SCHEDULETIME, SCHEDULETIME);
    }

    @Override
    public void loadMoreData() {

    }
}
