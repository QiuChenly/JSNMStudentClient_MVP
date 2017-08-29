package com.qiuchenly.stuclient.view.MainView.presenter;

import android.os.Handler;
import android.os.Looper;

import com.qiuchenly.stuclient.view.MainView.iMainViews;

import Basic.SharedPreferences.iViewGetPreference;
import Basic.SharedPreferences.sharePreference;

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

    public MainPresenterlmp(com.qiuchenly.stuclient.view.MainView.iMainViews iMainViews,
                            iViewGetPreference iViews) {
        this.iMainViews = iMainViews;
        handler = new Handler(Looper.getMainLooper());
        share = new sharePreference(iViews);
        iMainViews.getName(share.getStringPreference("userName"));
        iMainViews.getnick("暂无");
    }

    public void exitUser() {
        share.SavePreference("isLogin", false);
        iMainViews.exitUser();
    }
}
