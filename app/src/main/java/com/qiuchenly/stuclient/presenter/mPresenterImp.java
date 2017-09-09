package com.qiuchenly.stuclient.presenter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.qiuchenly.stuclient.model.RequestOnClick;
import com.qiuchenly.stuclient.model.RequestOnClickListener;
import com.qiuchenly.stuclient.model.RequestOnClicklmp;
import com.qiuchenly.stuclient.view.iViews;

import Basic.API.LoginAPI;
import Basic.API.Processresult;
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

    private LoginAPI api = null;

    public mPresenterImp(com.qiuchenly.stuclient.view.iViews iViews) {
        this.iViews = iViews;
        requestOnClick = new RequestOnClicklmp();
        handler = new Handler(Looper.getMainLooper());
    }


    public void fastLogin(String session) {
        requestOnClick.mFastLogin(session, new RequestOnClickListener() {
            @Override
            public void onSuccess(final String name,final String session,final int code) {
                //线程安全
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iViews.LoginSuccess(name,  session,code );
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
            public void onSuccess(final String name, final String session,final int code) {
                //线程安全
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iViews.LoginSuccess(name,session,code);
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

    public void sendMsg(final String PhoneNum, final Processresult processresult){
        new Thread(){
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
}
