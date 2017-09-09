package com.qiuchenly.stuclient.model;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface RequestOnClickListener {
    void onSuccess(String name, String session,int code);

    void onFailed(String errReson);
}
