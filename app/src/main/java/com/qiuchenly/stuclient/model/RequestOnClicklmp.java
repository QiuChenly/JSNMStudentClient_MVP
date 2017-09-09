package com.qiuchenly.stuclient.model;

import java.io.IOException;

import Basic.API.LoginAPI;
import Basic.API.LoginResult;
import Basic.API.Processresult;
import Basic.API.getImage;

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
                        public void onSuccess(String StudentName,String session,int code) {
                            listener.onSuccess(StudentName, session,code);
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
    public void mFastLogin(String session, RequestOnClickListener listener) {

    }

    @Override
    public void sendMsg(String PhoneNum, Processresult res) {
        new Thread(){}.start();
        sendSMS(PhoneNum,res);
    }


}
