package com.qiuchenly.stuclient.model;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public class RequestOnClicklmp implements RequestOnClick {

    @Override
    public void mLoginUser(String userName, String passWord, String vCode, RequestOnClickListener listener) {
        listener.onFailed();
    }
}
