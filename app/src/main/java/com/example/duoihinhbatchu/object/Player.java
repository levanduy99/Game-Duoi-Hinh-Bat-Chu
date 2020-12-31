package com.example.duoihinhbatchu.object;

import android.content.Context;
import android.content.SharedPreferences;

public class Player {

    private  String nameData = "appData";
    public int coin;

    public void saveState(Context ct){
        SharedPreferences setting = ct.getSharedPreferences (nameData,0);
        SharedPreferences.Editor editor = setting.edit ();
        editor.putInt ("coin",coin);
        editor.commit ();
    }

    public void getState(Context ct){
        SharedPreferences setting = ct.getSharedPreferences (nameData,0);
        coin = setting.getInt ("coin",20);
    }

}
