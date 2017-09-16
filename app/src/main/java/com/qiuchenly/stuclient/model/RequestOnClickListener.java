package com.qiuchenly.stuclient.model;

import com.qiuchenly.stuclient.Basic.API.HttpResponseUtils.loginRes;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface RequestOnClickListener {
    void onSuccess(String name, int code, String token, loginRes ret);

    void onFailed(String errReson);
}
