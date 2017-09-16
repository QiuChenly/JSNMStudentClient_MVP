package com.qiuchenly.stuclient.model;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.loginRes;
import com.qiuchenly.stuclient.Basic.API.Processresult;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */


public interface RequestOnClick {
    void mLoginUser(String userName, String passWord, RequestOnClickListener listener);

    void mFastLogin(RequestOnClickListener listener);

    void sendMsg(String PhoneNum, Processresult res);

    void setToken(String token);

    loginRes getLoginRes();

    void  getUserInfo(RequestOnClickListener ret);
}
