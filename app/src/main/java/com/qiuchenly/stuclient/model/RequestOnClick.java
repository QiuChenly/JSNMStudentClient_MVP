package com.qiuchenly.stuclient.model;

import Basic.API.getImage;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */


public interface RequestOnClick {
    void mLoginUser(String userName, String passWord, String vCode, RequestOnClickListener listener);

    void mGetVcode(getImage image);

    void mFastLogin(String session, RequestOnClickListener listener);
}
