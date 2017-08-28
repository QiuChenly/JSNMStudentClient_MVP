package com.qiuchenly.stuclient.view.MainView.presenter;

import android.os.Handler;
import android.os.Looper;

import com.qiuchenly.stuclient.view.MainView.iMainViews;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public class MainPresenterlmp {
    iMainViews iMainViews = null;
    private Handler handler=null;

    public MainPresenterlmp(com.qiuchenly.stuclient.view.MainView.iMainViews iMainViews) {
        this.iMainViews = iMainViews;
        handler=new Handler(Looper.getMainLooper());
    }
}
