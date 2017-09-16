package com.qiuchenly.stuclient.view.MainView;

import android.graphics.Bitmap;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.loginRes;
import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.ret_news;

/**
 * Create By QiuChenly
 */

public interface iMainViews {
    void getNews();

    void getImage(Bitmap bitmap);

    void getName(String name);

    void getnick(String nick, loginRes ret);

    void exitUser();

    void getSchoolNews(ret_news ret);

}
