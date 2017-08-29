package com.qiuchenly.stuclient.model;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface RequestOnClickListener {
    void onSuccess(String name, boolean isLeader, String session);

    void onFailed(String errReson);
}
