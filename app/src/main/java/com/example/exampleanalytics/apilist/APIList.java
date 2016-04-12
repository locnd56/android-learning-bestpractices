package com.example.exampleanalytics.apilist;

/**
 * Created by Mr.Incredible on 4/12/2016.
 */
public class APIList {
    public static final String KEY_LOGIN = "KEY_LOGIN";


    public static final String androidhiveAPI = "http://api.androidhive.info/contacts/";
    public static final String LoginAPI = "http://m.fss.com.vn/MFlexTest/MAccount/Login";
    public static final String LoginAPIParam = "{\"username\":\"%s\",\"password\":\"%s\"}";

    public static String formatParams(String s, String... params) {
        return String.format(s, params);
    }
}
