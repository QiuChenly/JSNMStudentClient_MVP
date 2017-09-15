package com.qiuchenly.stuclient.Basic.API;

public interface LoginResult {
    void onSuccess(String StudentName,int code,String token);
    void onFailed(String ErrReason);
}
