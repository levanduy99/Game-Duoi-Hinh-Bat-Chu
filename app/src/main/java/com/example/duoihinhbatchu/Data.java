package com.example.duoihinhbatchu;

import com.example.duoihinhbatchu.object.Riddle;

import java.util.ArrayList;

public class Data {
    private static Data data;
    static{
        data = new Data();
    }
    public static Data getData(){
        return data;
    }
    public ArrayList<Riddle> arrRiddle = new ArrayList<> ();
}
