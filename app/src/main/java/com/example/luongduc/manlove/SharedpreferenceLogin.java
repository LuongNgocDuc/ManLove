package com.example.luongduc.manlove;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedpreferenceLogin {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public int PRE_MODE =1;

    private static final String KEY_LOGIN="isLogin";
    private static final String NAME="android_demo";

    @SuppressLint("WrongConstant")
    public SharedpreferenceLogin(){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(NAME,PRE_MODE);
        editor = sharedPreferences.edit();
    }
    public void SetLogin(Boolean isLogin){
        editor.putBoolean(KEY_LOGIN,isLogin);
        editor.commit();
    }
    public boolean Check(){
        return sharedPreferences.getBoolean(KEY_LOGIN,false);
    }
}
