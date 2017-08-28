package com.qiuchenly.stuclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.qiuchenly.stuclient.view.LoginActivity;

import Basic.BaseApp;
import Basic.SharedPreferences.iViewGetPreference;
import Basic.SharedPreferences.sharePreference;

public class SplashActivity extends BaseApp implements iViewGetPreference {

    sharePreference share = null;

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
        startAnimation(t, false, 1);
        share = new sharePreference(this);

        userName=share.getStringPreference("userName");
        session

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
                        startAnimation(btn, true, 2);
                        break;
                    case 2:
                        btn.setText("QiuChenly Design");
                        startAnimation(btn, false, 3);
                        break;
                    case 3:
                        startAnimation(btn, true, 4);
                        break;
                    case 4:
                        startActivity(LoginActivity.class, true);
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
}
