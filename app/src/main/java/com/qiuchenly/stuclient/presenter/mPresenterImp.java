package com.qiuchenly.stuclient.presenter;

import android.os.Handler;
import android.os.Looper;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.loginRes;
import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.ret_news;
import com.qiuchenly.stuclient.model.RequestOnClick;
import com.qiuchenly.stuclient.model.RequestOnClickListener;
import com.qiuchenly.stuclient.model.RequestOnClicklmp;
import com.qiuchenly.stuclient.view.iViews;

import com.qiuchenly.stuclient.Basic.API.Processresult;


/**
 * Author : QiuChenLy
 * Date    : 2017.8.27 周日 PM 3:41
 * Func    : 逻辑处理层
 * LastEdit: 2017.8.27 周日 PM 3:41
 */

public class mPresenterImp {
    private iViews iViews = null;
    public static RequestOnClick requestOnClick = null;
    private Handler handler = null;

    public mPresenterImp(com.qiuchenly.stuclient.view.iViews iViews) {
        this.iViews = iViews;
        requestOnClick = new RequestOnClicklmp();
        handler = new Handler(Looper.getMainLooper());
    }


    public void fastLogin() {
        requestOnClick.mFastLogin(new RequestOnClickListener() {
            @Override
            public void onSuccess(String name, int code, String token, loginRes ret) {

            }

            @Override
            public void onSuccess(final String name, final int code, final String token, final ret_news ret) {

            }

            @Override
            public void onSuccess(final String name, final int code, final String token) {
                //线程安全
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iViews.LoginSuccess(name, code, token);
                    }
                });
            }

            @Override
            public void onFailed(final String errReson) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iViews.LoginFailed(errReson);
                    }
                }, 1);
            }
        });
    }

    public void login(String userName, String passWord) {
        requestOnClick.mLoginUser(userName, passWord, new RequestOnClickListener() {
            @Override
            public void onSuccess(String name, int code, String token, loginRes ret) {

            }

            @Override
            public void onSuccess(final String name, final int code, final String token, ret_news ret) {

            }

            @Override
            public void onSuccess(final String name, final int code, final String token) {
                //线程安全
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iViews.LoginSuccess(name, code, token);
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
                }, 1);
            }
        });
    }

    public void sendMsg(final String PhoneNum, final Processresult processresult) {
        new Thread() {
            @Override
            public void run() {
                requestOnClick.sendMsg(PhoneNum, new Processresult() {
                    @Override
                    public void onFailed() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                processresult.onFailed();
                            }
                        });
                    }

                    @Override
                    public void onSuccess(final String randomKey) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                processresult.onSuccess(randomKey);
                            }
                        });
                    }
                });
            }
        }.start();
    }

    public void setToken(String token) {
        requestOnClick.setToken(token);
    }
}
