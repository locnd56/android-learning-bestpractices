package com.locnd.appbase.serverconnection;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Mr.Incredible on 6/21/2016.
 */
public class ConnectionData {

    AsyncHttpClient client;
    private static final String HEADER_CONTENT_TYPE = "Content-type";
    private static final String VALUE_CONTENT_TYPE = "application/json";

    public synchronized ConnectionData getInstance() {
        return new ConnectionData();
    }

    public ConnectionData() {
        client = new AsyncHttpClient();
        client.addHeader(HEADER_CONTENT_TYPE, VALUE_CONTENT_TYPE);
    }

    public void callGetHttpRequest(final RequestObject obj, final IResponseResult result) {
        client.get(obj.getApi(), obj.getParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                ResponseObject responseObject = new ResponseObject(statusCode, obj.getKeyIdentifier(), response);
                result.response(responseObject);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
//                ResponseObject responseObject = new ResponseObject(statusCode, obj.getKeyIdentifier(), responseString);
            }
        });
    }
    public void callPostHttpRequest(final RequestObject obj,final IResponseResult result){
        client.post(obj.getApi(), obj.getParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                ResponseObject responseObject = new ResponseObject(statusCode, obj.getKeyIdentifier(), response);
                result.response(responseObject);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
//                ResponseObject responseObject = new ResponseObject(statusCode, obj.getKeyIdentifier(), responseString);
            }
        });
    }

    //DECODING WITH GSON LIBRARY

}
