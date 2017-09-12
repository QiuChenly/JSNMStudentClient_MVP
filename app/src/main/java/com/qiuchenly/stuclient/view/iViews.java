package com.qiuchenly.stuclient.view;

import android.graphics.Bitmap;

/**
 * Author : QiuChenLy
 * Date    : 2017.8.27 周日 PM 3:52
 * Func    : V层交互处理
 * LastEdit: 2017.8.27 周日 PM 3:52
 */

public interface iViews {
    void LoginSuccess(String name ,int Code,String token);

    void LoginFailed(String reason);

    <T> void showToasts(T msg);
}
