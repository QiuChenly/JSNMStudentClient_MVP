package com.qiuchenly.stuclient.biz;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */


public interface RequestOnClick {
    void mLoginUser(String userName,String passWord,String vCode,RequestOnClickListener listener);
}
