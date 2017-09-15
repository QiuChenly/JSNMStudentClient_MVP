package com.qiuchenly.stuclient.Basic.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public class sharePreference {
    private SharedPreferences sp = null;
    private iViewGetPreference iViewGetPreference = null;

    public sharePreference(iViewGetPreference i) {
        this.iViewGetPreference = i;
        this.sp = iViewGetPreference.getInstance().getSharedPreferences("QiuChen", Context.MODE_PRIVATE);
    }

    public void SavePreference(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public void SavePreference(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public void SavePreference(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public String getStringPreference(String key) {
        return sp.getString(key, "");
    }

    public int getIntPreference(String key) {
        return sp.getInt(key, -1);
    }

    public boolean getBooleanPreference(String key) {
        return sp.getBoolean(key, false);
    }
}
