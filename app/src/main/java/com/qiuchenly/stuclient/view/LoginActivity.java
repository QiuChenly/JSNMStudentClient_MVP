package com.qiuchenly.stuclient.view;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.qiuchenly.stuclient.R;
import com.qiuchenly.stuclient.presenter.mPresenterImp;
import com.qiuchenly.stuclient.view.iViews;

import Basic.BaseApp;

public class LoginActivity extends BaseApp implements iViews {
    com.dd.CircularProgressButton circularProgressButton = null;
    TextView tLogin, tPass;
    mPresenterImp mPresenterImp = null;

    public LoginActivity() {
        super();
    }

    @Override
    public void loadComplete() {
        mPresenterImp = new mPresenterImp(this);
    }

    @Override
    public void SetOnClickListener() {
        circularProgressButton = $(R.id.bLoginButton, true);
        circularProgressButton.setIndeterminateProgressMode(true);
        tLogin = $(R.id.tlogin_user, false);
        tPass = $(R.id.tlogin_pass, false);
    }

    @Override
    public void BeClick(View v) {
        switch (v.getId()) {
            case R.id.bLoginButton:
                circularProgressButton.setClickable(false);
                circularProgressButton.setProgress(1);
                break;
            default:
                break;
        }
    }

    @Override
    public int SetThisContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void LoginSuccess() {
        circularProgressButton.setProgress(100);
    }

    @Override
    public void LoginFailed() {
        circularProgressButton.setProgress(-1);
        circularProgressButton.setClickable(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                circularProgressButton.setProgress(0);
            }
        },1200);
    }
}
