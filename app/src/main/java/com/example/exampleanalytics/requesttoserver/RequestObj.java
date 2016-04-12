package com.example.exampleanalytics.requesttoserver;

import java.util.HashMap;

/**
 * Created by giang.ngo on 11-11-2015.
 */
public class RequestObj {
    private HashMap<String, Object> info;
    String apiIdentify;
    String api;
    String parameter;

    public RequestObj(String api, String apiIdentify) {
        this.api = api;
        this.apiIdentify = apiIdentify;
    }

    public String getApiIdentify() {
        return apiIdentify;
    }

    public Object getInfo(String key) {
        return info.get(key);
    }

    public String getParameter() {
        return parameter;
    }

    public String getAPI() {
        return api;
    }

    public void setInfo(String key, Object value) {
        info.put(key, value);
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
