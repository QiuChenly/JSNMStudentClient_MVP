package com.qiuchenly.stuclient.view.MainView;

import android.support.v4.view.ViewPager;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.ret_news;

/**
 * Created by QiuChen on 2017/9/16.
 */

public interface news_interface {
    void nav_news(ViewPager v, ret_news ret);
}
