package com.locnd.appbase.requesttoserver;

import org.apache.http.client.methods.HttpPost;

/**
 * Created by giang.ngo on 05-01-2016.
 */
public class HttpPatch extends HttpPost {
    public static final String METHOD_PATCH = "PATCH";

    public HttpPatch(final String url) {
        super(url);
    }

    @Override
    public String getMethod() {
        return METHOD_PATCH;
    }
}
