package com.choosedormitory;

import com.google.gson.Gson;

/**
 * Created by amoshua on 23/11/2017.
 */

public class Util {
    private static Gson gson = new Gson();
    public static String TAG_SDUDENT = "student";
    public static Student student;
    public static Room room;
    public static Gson GetGsonInstance(){
        return gson;
    }
}
