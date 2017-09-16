package com.qiuchenly.stuclient.model;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.loginRes;
import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.ret_news;
import com.qiuchenly.stuclient.Basic.API.LoginAPI;
import com.qiuchenly.stuclient.Basic.API.LoginResult;
import com.qiuchenly.stuclient.Basic.API.Processresult;

/**
 * Author : QiuChenLy
 * Date    : 2017.8.27 周日 PM4:56
 * Func    : model数据层
 * LastEdit: 2017.8.27 周日 PM4:56
 */

public class RequestOnClicklmp extends LoginAPI implements RequestOnClick {
    @Override
    public void mLoginUser(final String userName, final String passWord,
                           final RequestOnClickListener listener) {
        new Thread() {
            @Override
            public void run() {
                loginH5(userName, passWord, new LoginResult() {
                    @Override
                    public void onSuccess(String StudentName, int code, String token) {
                        listener.onSuccess(StudentName, code, token);
                    }

                    @Override
                    public void onFailed(String ErrReason) {
                        listener.onFailed(ErrReason);
                    }
                });
            }
        }.start();

    }

    @Override
    public void mFastLogin(final RequestOnClickListener listener) {
        new Thread() {
            @Override
            public void run() {
                skipLogin(new LoginResult() {
                    @Override
                    public void onSuccess(String StudentName, int code, String token) {
                        listener.onSuccess(StudentName, code, token);
                    }

                    @Override
                    public void onFailed(String ErrReason) {
                        listener.onFailed(ErrReason);
                    }
                });
            }
        }.start();
    }

    @Override
    public void sendMsg(final String PhoneNum, final Processresult res) {
        new Thread() {
            @Override
            public void run() {
                sendSMS(PhoneNum, res);
            }
        }.start();

    }

    @Override
    public void setToken(String token) {
        super.setToken(token);
    }

    @Override
    public loginRes getLoginRes() {
        return super.loginRes;
    }

    @Override
    public void getUserInfo(final RequestOnClickListener rets) {
        new Thread() {
            @Override
            public void run() {
                com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.loginRes res = getUserInfo();

                if (res != null) {
                    rets.onSuccess(null, 0, null, res);
                } else {
                    rets.onFailed("访问网络失败!");
                }
            }
        }.start();
    }

    @Override
    public void getSchoolNew(final RequestOnClickListener requestOnClick) {
        new Thread(){
            @Override
            public void run() {
                ret_news ret_new= getSchoolNews();
                if(ret_new!=null){
                    requestOnClick.onSuccess(null,0,null,ret_new);
                }else
                    requestOnClick.onFailed("数据出错!");
            }
        }.start();
    }
}
