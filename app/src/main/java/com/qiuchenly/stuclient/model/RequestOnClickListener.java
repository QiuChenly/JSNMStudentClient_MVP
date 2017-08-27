package com.qiuchenly.stuclient.model;

import java.util.List;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface RequestOnClickListener {
    void onSuccess(String name,boolean isLeader,String session);
    void onFailed(String errReson);
}
