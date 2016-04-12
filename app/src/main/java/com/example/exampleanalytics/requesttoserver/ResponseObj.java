package com.example.exampleanalytics.requesttoserver;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by giang.ngo on 11-11-2015.
 */


public class ResponseObj {
    private HashMap<String, Object> info;
    String apiIdentify;
    private ResponseStatus statusCode;
    private JSONObject data;

    public ResponseObj(ResponseStatus statusCode, String apiIdentify) {
        info = new HashMap<String, Object>();
        this.apiIdentify = apiIdentify;
        this.statusCode = statusCode;
    }

    public String getApiIdentify() {
        return apiIdentify;
    }

    public ResponseStatus getStatusrCode() {
        return statusCode;
    }

    public JSONObject getData() {
        return data;
    }

    public Object getInfo(String key) {
        return info.get(key);
    }

    public void setInfo(String key, Object value) {
        info.put(key, value);
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

}
