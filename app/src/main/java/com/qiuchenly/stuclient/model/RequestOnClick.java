package com.qiuchenly.stuclient.model;

import Basic.API.Processresult;
import Basic.API.getImage;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */


public interface RequestOnClick {
    void mLoginUser(String userName, String passWord,RequestOnClickListener listener);

    void mFastLogin(String session, RequestOnClickListener listener);

    void sendMsg(String PhoneNum, Processresult res);
}
