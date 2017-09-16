package com.qiuchenly.stuclient.view.MainView.presenter;

import android.os.Handler;
import android.os.Looper;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.loginRes;
import com.qiuchenly.stuclient.model.RequestOnClick;
import com.qiuchenly.stuclient.model.RequestOnClickListener;
import com.qiuchenly.stuclient.presenter.mPresenterImp;
import com.qiuchenly.stuclient.view.MainView.iMainViews;

import com.qiuchenly.stuclient.Basic.SharedPreferences.iViewGetPreference;
import com.qiuchenly.stuclient.Basic.SharedPreferences.sharePreference;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public class MainPresenterlmp {
    iMainViews iMainViews = null;
    private Handler handler = null;
    sharePreference share = null;
    RequestOnClick requestOnClick = null;


    public MainPresenterlmp(final com.qiuchenly.stuclient.view.MainView.iMainViews iMainViews,
                            iViewGetPreference iViews) {
        this.iMainViews = iMainViews;
        handler = new Handler(Looper.getMainLooper());
        share = new sharePreference(iViews);
        requestOnClick = mPresenterImp.requestOnClick;
        loginRes ret=requestOnClick.getLoginRes();
        iMainViews.getName(ret.userBaseInfo.realName);
        handler =new Handler(Looper.getMainLooper());

        requestOnClick.getUserInfo(new RequestOnClickListener() {
            @Override
            public void onSuccess(String name, int code, String token, final loginRes ret) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iMainViews.getnick(ret.userBaseInfo.collegeName,ret);
                    }
                });

            }

            @Override
            public void onFailed(String errReson) {

            }
        });
        iMainViews.getNews();
    }

    public void exitUser() {
        share.SavePreference("isLogin", false);
        iMainViews.exitUser();
    }

}
