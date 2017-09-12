package com.qiuchenly.stuclient.model;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface RequestOnClickListener {
    void onSuccess(String name, int code,String token);

    void onFailed(String errReson);
}
