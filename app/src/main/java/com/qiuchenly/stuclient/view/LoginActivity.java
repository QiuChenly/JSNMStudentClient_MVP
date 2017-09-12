package com.qiuchenly.stuclient.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qiuchenly.stuclient.R;
import com.qiuchenly.stuclient.presenter.mPresenterImp;
import com.qiuchenly.stuclient.view.LoginViewPagerAdapter.viewPager;
import com.qiuchenly.stuclient.view.MainView.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Basic.API.Processresult;
import Basic.BaseApp;
import Basic.SharedPreferences.iViewGetPreference;
import Basic.SharedPreferences.sharePreference;

public class LoginActivity extends BaseApp implements iViews, iViewGetPreference, View.OnTouchListener,Processresult {
    com.dd.CircularProgressButton circularProgressButton = null, bBindBtn;
    TextView tLogin, tPass, tForgetPass, tNoLoginIn, tnotgetVcode, tsendSMS,tPhoneNums,tPhoneVcode;
    ViewPager switchViews;
    mPresenterImp mPresenterImp = null;
    sharePreference share = null;
    FrameLayout fcancelbind;

    public LoginActivity() {
        super();
    }

    @Override
    public void loadComplete() {
        mPresenterImp = new mPresenterImp(this);
        share = new sharePreference(this);
    }

    @Override
    public void SetOnClickListener() {
        switchViews = $(R.id.vlogin_switchViews, false);
        List<View> list = new ArrayList<>();
        list.add(getLayoutInflater().inflate(R.layout.loginmain, null));
        list.add(getLayoutInflater().inflate(R.layout.loginphonebind, null));
        switchViews.setAdapter(new viewPager(list));
        switchViews.setOffscreenPageLimit(2);
        switchViews.setOnTouchListener(this);
        //此时对象放置在list中的第一个View里面，直接使用默认的this会导致找不到View而闪退
        circularProgressButton = $(R.id.bLoginButton, true, list.get(0));
        circularProgressButton.setIndeterminateProgressMode(true);
        tLogin = $(R.id.tlogin_user, false, list.get(0));
        tPass = $(R.id.tlogin_pass, false, list.get(0));


        /**
         * TestStart
         */
        tLogin.setText("201613549");
        tPass.setText("068411");
        tForgetPass = $(R.id.tForgetPassword, true, list.get(0));
        tNoLoginIn = $(R.id.tNoLoginIn, true, list.get(0));
        fcancelbind = $(R.id.fcancelbind, true, list.get(1));
        tnotgetVcode = $(R.id.tnotgetVcode, true, list.get(1));
        bBindBtn = $(R.id.bBindBtn, true, list.get(1));
        tsendSMS = $(R.id.tsendSMS, true, list.get(1));
        tPhoneNums=$(R.id.tPhoneNums,false,list.get(1));
        tPhoneVcode=$(R.id.tPhoneVcode,false,list.get(1));

    }

    int i = 0;
    Timer timer;

    @Override
    public void BeClick(View v) {
        switch (v.getId()) {
            case R.id.bLoginButton:
                circularProgressButton.setClickable(false);
                circularProgressButton.setProgress(1);
                //if (validation(tLogin.getText().toString(), 1) || validation(tPass.getText().toString(), 2)) {
                mPresenterImp.login(tLogin.getText().toString(), tPass.getText().toString());
                //}
                break;
            case R.id.bBindBtn:
                circularProgressButton.setClickable(false);
                circularProgressButton.setProgress(1);
                //TODO 开始实现绑定手机号码操作
                break;
            case R.id.tForgetPassword:
                showToasts("请联系你们老师或回忆自己设定的密码~");
                break;
            case R.id.tnotgetVcode:
                showToasts("那这就很尴尬了~换个手机号码试试吧！");
                break;
            case R.id.tNoLoginIn:
                share.SavePreference("userName", "游客");
                showToasts("登录成功!欢迎你 游客");
                startActivity(Main.class, true);
                break;
            case R.id.fcancelbind:
                switchViews.setCurrentItem(0);
                break;
            case R.id.tsendSMS:
                String code=tPhoneNums.getText().toString();
                if(code.length()<=0){
                    showToasts("手机号码不能为空！");
                    return ;
                }
                i = 60;
                tsendSMS.setEnabled(false);
                timer = new Timer();
                //发送手机验证码
                mPresenterImp.sendMsg(code,this);
                break;
            default:
                break;
        }
    }

    boolean validation(String text, int Category) {
        if (text == null && text.length() <= 0) {
            switch (Category) {
                case 1:

                    break;
                case 2:
                    break;
            }
            return false;
        }
        return true;
    }

    @Override
    public int SetThisContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void LoginSuccess(final String name , int code,String token) {
        if (code == 100004) {
            switchViews.setCurrentItem(1);
            circularProgressButton.setProgress(0);
            circularProgressButton.setClickable(true);
            return;
        }
        circularProgressButton.setProgress(100);
        share.SavePreference("session", token);
        share.SavePreference("userName", name);
        share.SavePreference("id", tLogin.getText().toString());
        share.SavePreference("password", tPass.getText().toString());
        share.SavePreference("isLogin", true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showToasts("登录成功!欢迎你 " + name);
                startActivity(Main.class, true);
            }
        }, 1000);
    }

    @Override
    public void LoginFailed(final String reason) {
        circularProgressButton.setProgress(-1);
        share.SavePreference("isLogin", false);
        share.SavePreference("session", "");
        showToast(reason);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                circularProgressButton.setProgress(0);
                circularProgressButton.setClickable(true);
            }
        }, 1000);
    }

    @Override
    public <T> void showToasts(T msg) {
        showToast(msg);
    }

    @Override
    public Context getInstance() {
        return this;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switchViews.requestDisallowInterceptTouchEvent(true);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if (switchViews.getCurrentItem() > 0) {
                switchViews.setCurrentItem(0);
                try {
                    timer.cancel();
                } catch (Exception e) {
                    Log.d("QiuChen", "时钟出闪退BUG了 ---- " + e.getLocalizedMessage());
                }
                tsendSMS.setEnabled(true);
                tsendSMS.setText("发送");
                return true;
            }
        }
        super.onKeyDown(keyCode, event);
        return true;
    }

    @Override
    public void onFailed() {
        showToasts("验证码发送失败！");
    }

    @Override
    public void onSuccess(String randomKey) {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                i--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (i <= 0) {
                            tsendSMS.setEnabled(true);
                            tsendSMS.setText("发送");
                            timer.cancel();
                            return;
                        }
                        tsendSMS.setText(i + "秒");
                    }
                });
            }
        };
        timer.schedule(task, 0, 1000);
        showToasts("验证码发送完成。");
        tPhoneVcode.setText(randomKey);
        showToasts("获取验证码完成。");
    }
}
