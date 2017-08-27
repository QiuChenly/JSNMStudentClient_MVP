package com.qiuchenly.stuclient.presenter;

import android.os.*;

import com.qiuchenly.stuclient.model.*;
import com.qiuchenly.stuclient.view.iViews;

import java.util.List;


/**
 * Author : QiuChenLy
 * Date    : 2017.8.27 周日 PM 3:41
 * Func    : 逻辑处理层
 * LastEdit: 2017.8.27 周日 PM 3:41
 */

public class mPresenterImp {
    private iViews iViews = null;
    RequestOnClick requestOnClick = null;
    private Handler handler = null;

    public mPresenterImp(com.qiuchenly.stuclient.view.iViews iViews) {
        this.iViews = iViews;
        requestOnClick = new RequestOnClicklmp();
        handler = new Handler(Looper.getMainLooper());
    }
    public void login(String userName,String passWord,String vCode){
        requestOnClick.mLoginUser(userName, passWord, vCode, new RequestOnClickListener() {
            @Override
            public void onSuccess(List<String> data) {
                //线程安全
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iViews.LoginSuccess();
                    }
                });
            }

            @Override
            public void onFailed() {
                iViews.LoginFailed();
            }
        });
    }
}
