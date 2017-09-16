package com.qiuchenly.stuclient.model;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.loginRes;
import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.ret_news;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface RequestOnClickListener {
    void onSuccess(String name, int code, String token, loginRes ret);
    void onSuccess(String name, int code, String token, ret_news ret);
    void onSuccess(String name, int code, String token);

    void onFailed(String errReson);
}
