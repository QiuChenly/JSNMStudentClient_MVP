package com.qiuchenly.stuclient.presenter;

import android.graphics.Bitmap;
import android.os.*;

import com.qiuchenly.stuclient.model.*;
import com.qiuchenly.stuclient.view.iViews;

import java.util.List;

import Basic.API.LoginAPI;
import Basic.API.getImage;


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

    private LoginAPI api=null;

    public mPresenterImp(com.qiuchenly.stuclient.view.iViews iViews) {
        this.iViews = iViews;
        requestOnClick = new RequestOnClicklmp();
        handler = new Handler(Looper.getMainLooper());
    }
    public void login(String userName,String passWord,String vCode){
        requestOnClick.mLoginUser(userName, passWord, vCode, new RequestOnClickListener() {
            @Override
            public void onSuccess(final String name, final boolean isLeader, final String session) {
                //线程安全
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iViews.LoginSuccess(name,isLeader,session);
                    }
                });
            }

            @Override
            public void onFailed(final String reason) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iViews.LoginFailed(reason);
                    }
                },2000);
            }
        });
    }

    public void getcode(){
        requestOnClick.mGetVcode(new getImage() {
            @Override
            public void onSuccess(final Bitmap bitmap) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iViews.switchVcode(bitmap);
                    }
                });

            }

            @Override
            public void onFailed(String reason) {
                iViews.showToasts(reason);
            }
        });

    }
}
