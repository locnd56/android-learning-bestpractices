package com.locnd.appbase.serverconnection;

import com.loopj.android.http.RequestParams;

/**
 * Created by Mr.Incredible on 6/21/2016.
 */
public class RequestObject {
    String api;
    RequestParams params;
    String keyIdentifier;

    public RequestObject(String api, RequestParams params, String keyIdentifier) {
        this.api = api;
        this.params = params;
        this.keyIdentifier = keyIdentifier;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public RequestParams getParams() {
        return params;
    }

    public void setParams(RequestParams params) {
        this.params = params;
    }

    public String getKeyIdentifier() {
        return keyIdentifier;
    }

    public void setKeyIdentifier(String keyIdentifier) {
        this.keyIdentifier = keyIdentifier;
    }
}
