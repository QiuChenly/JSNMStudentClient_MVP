package com.qiuchenly.stuclient.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiuchenly.stuclient.R;
import com.qiuchenly.stuclient.presenter.mPresenterImp;
import com.qiuchenly.stuclient.view.MainView.Main;

import Basic.BaseApp;
import Basic.SharedPreferences.iViewGetPreference;
import Basic.SharedPreferences.sharePreference;

public class LoginActivity extends BaseApp implements iViews,iViewGetPreference {
    com.dd.CircularProgressButton circularProgressButton = null;
    TextView tLogin, tPass,tLoginVcode,tForgetPass;
    mPresenterImp mPresenterImp = null;
    ImageView iVcode=null;
    sharePreference share = null;

    public LoginActivity() {
        super();
    }

    @Override
    public void loadComplete() {
        mPresenterImp = new mPresenterImp(this);
        mPresenterImp.getcode();
        share = new sharePreference(this);
    }

    @Override
    public void SetOnClickListener() {
        circularProgressButton = $(R.id.bLoginButton, true);
        circularProgressButton.setIndeterminateProgressMode(true);
        tLogin = $(R.id.tlogin_user, false);
        tPass = $(R.id.tlogin_pass, false);
        tLoginVcode=$(R.id.tlogin_vcode,false);
        iVcode=$(R.id.iVcode,true);
        tForgetPass=$(R.id.tForgetPassword,true);
    }

    @Override
    public void BeClick(View v) {
        switch (v.getId()) {
            case R.id.bLoginButton:
                circularProgressButton.setClickable(false);
                circularProgressButton.setProgress(1);
                //if (validation(tLogin.getText().toString(), 1) || validation(tPass.getText().toString(), 2)) {
                    mPresenterImp.login(tLogin.getText().toString(), tPass.getText().toString(), tLoginVcode.getText().toString());
                //}
                break;
            case R.id.iVcode:
                mPresenterImp.getcode();
                break;
            case R.id.tForgetPassword:
                showToasts("请联系你们老师或回忆自己设定的密码~");
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
    public void LoginSuccess(final String name, boolean isLeader, String session) {
        circularProgressButton.setProgress(100);
        share.SavePreference("session",session);
        share.SavePreference("userName",name);
        share.SavePreference("id",tLogin.getText().toString());
        share.SavePreference("password",tPass.getText().toString());
        share.SavePreference("isLogin",true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showToasts("登录成功!欢迎你 "+name);
                startActivity(Main.class,true);
            }
        }, 1000);
    }

    @Override
    public void LoginFailed(final String reason) {
        circularProgressButton.setProgress(-1);
        share.SavePreference("isLogin",false);
        share.SavePreference("session","");
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
    public void switchVcode(Bitmap Vcode) {
        iVcode.setImageBitmap(Vcode);
    }

    @Override
    public <T> void showToasts(T msg) {
        showToast(msg);
    }

    @Override
    public Context getInstance() {
        return this;
    }
}
