package com.locnd.appbase.serverconnection;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Mr.Incredible on 6/21/2016.
 */
public class ResponseObject {
    HashMap<String, Object> info;
    int statusCode;
    String keyApiIdentifier;
    Object data;

    public ResponseObject(int statusCode, String keyApiIdentifier, Object data) {
        this.statusCode = statusCode;
        this.keyApiIdentifier = keyApiIdentifier;
        this.data = data;
    }

    public HashMap<String, Object> getInfo() {
        return info;
    }

    public void setInfo(HashMap<String, Object> info) {
        this.info = info;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getKeyApiIdentifier() {
        return keyApiIdentifier;
    }

    public void setKeyApiIdentifier(String keyApiIdentifier) {
        this.keyApiIdentifier = keyApiIdentifier;
    }

    public Object getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
