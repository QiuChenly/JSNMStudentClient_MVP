package com.qiuchenly.stuclient.view.MainView.presenter;

import android.os.Handler;
import android.os.Looper;

import com.qiuchenly.stuclient.model.RequestOnClick;
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


    public MainPresenterlmp(com.qiuchenly.stuclient.view.MainView.iMainViews iMainViews,
                            iViewGetPreference iViews) {
        this.iMainViews = iMainViews;
        handler = new Handler(Looper.getMainLooper());
        share = new sharePreference(iViews);
        requestOnClick = mPresenterImp.requestOnClick;
        iMainViews.getName(requestOnClick.getLoginRes().userBaseInfo.realName);
        iMainViews.getnick(requestOnClick.getLoginRes().userBaseInfo.collegeName);
        iMainViews.getNews();
    }

    public void exitUser() {
        share.SavePreference("isLogin", false);
        iMainViews.exitUser();
    }

}
