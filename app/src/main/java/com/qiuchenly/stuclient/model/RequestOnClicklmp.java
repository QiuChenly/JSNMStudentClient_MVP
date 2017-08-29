package com.qiuchenly.stuclient.model;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.IOException;
import java.util.BitSet;

import Basic.API.LoginAPI;
import Basic.API.LoginResult;
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
                           final String vCode, final RequestOnClickListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    login(userName, passWord, vCode, new LoginResult() {
                        @Override
                        public void onSuccess(String StudentName, boolean isLeader, String session) {
                            listener.onSuccess(StudentName, isLeader, session);
                        }

                        @Override
                        public void onFailed(String ErrReason) {
                            listener.onFailed(ErrReason);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onFailed("向服务器请求发送异常!");
                }
            }
        }.start();

    }

    @Override
    public void mGetVcode(getImage image) {
        getImage(image);
    }

    @Override
    public void mFastLogin(final String session, final RequestOnClickListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    fastLogin(session, new LoginResult() {
                        @Override
                        public void onSuccess(String StudentName, boolean isLeader, String session) {
                            listener.onSuccess(StudentName, isLeader, session);
                        }

                        @Override
                        public void onFailed(String ErrReason) {
                            listener.onFailed(ErrReason);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
