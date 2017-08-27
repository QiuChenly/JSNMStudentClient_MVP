package com.qiuchenly.stuclient.biz;

import java.util.List;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface RequestOnClickListener {
    void onSuccess(List<String> data);
    void onFailed();
}
