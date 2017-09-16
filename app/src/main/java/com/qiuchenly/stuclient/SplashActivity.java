package com.qiuchenly.stuclient;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.qiuchenly.stuclient.presenter.mPresenterImp;
import com.qiuchenly.stuclient.view.LoginActivity;
import com.qiuchenly.stuclient.view.MainView.Main;
import com.qiuchenly.stuclient.view.iViews;

import com.qiuchenly.stuclient.Basic.BaseApp;
import com.qiuchenly.stuclient.Basic.SharedPreferences.iViewGetPreference;
import com.qiuchenly.stuclient.Basic.SharedPreferences.sharePreference;

public class SplashActivity extends BaseApp implements iViewGetPreference, iViews {

    sharePreference share = null;

    mPresenterImp presenterImp = null;


    String userName;
    String session;
    boolean isLogin = false;

    //配置页面基本属性
    public SplashActivity() {
        super(false, true, true);
    }

    @Override
    public int SetThisContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public void loadComplete() {
        TextView t = $(R.id.tSplashTitle, true);
        share = new sharePreference(this);
        presenterImp = new mPresenterImp(this);
        startAnimation(t, false, 1);
    }

    void startAnimation(final TextView btn, final boolean isHide, final int STATUS) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(isHide ? 1f : 0f, isHide ? 0f : 1f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch (STATUS) {
                    case 1:
                        startAnimation(btn, true, 4);
                        break;
                    case 2:
                        btn.setText("全新架构 从\"芯\"出发");
                        startAnimation(btn, false, 3);
                        break;
                    case 3:
                        startAnimation(btn, true, 4);
                        break;
                    case 4:
                        btn.setText("QiuChenly originality");
                        startAnimation(btn, false, 5);
                        break;
                    case 5:
                        startAnimation(btn, true, 6);
                        break;
                    case 6:
                        if ((isLogin = share.getBooleanPreference("isLogin")&& (session = share.getStringPreference("session"))!="")) {
                            userName = share.getStringPreference("userName");
                            presenterImp.setToken(session);
                            presenterImp.fastLogin();
                        } else {
                            startActivity(LoginActivity.class, true);
                        }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btn.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }

    @Override
    public void SetOnClickListener() {

    }

    @Override
    public void BeClick(View v) {

    }

    @Override
    public Context getInstance() {
        return this;
    }

    @Override
    public void LoginSuccess(String name,int code,String token) {
        showToast("Hi 欢迎回来 " + userName);
        share.SavePreference("session",token);
        share.SavePreference("isLogin", true);
        startActivity(Main.class, true);
    }

    @Override
    public void LoginFailed(String reason) {
        showToast("呀~快速登录失效啦!请您手动登录~\n服务器返回信息:" + reason,true);
        share.SavePreference("session", "");
        share.SavePreference("isLogin", false);
        startActivity(LoginActivity.class, true);
    }

    @Override
    public <T> void showToasts(T msg) {
//无需实现
    }

}
