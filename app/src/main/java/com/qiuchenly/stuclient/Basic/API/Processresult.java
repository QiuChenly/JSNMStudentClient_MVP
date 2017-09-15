package com.qiuchenly.stuclient.Basic.API;

/**
 * Created by QiuChenly on 2017/9/9.
 */

public interface Processresult {
    void onFailed();
    void onSuccess(String randomKey);
}
