package com.example.exampleanalytics.requesttoserver;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/**
 * Created by giang.ngo on 18-03-2016.
 */
class ServiceConnection {
    private static final int THREAD_POOL = 20;
    private static final int TIMEOUT = 20000;
    public static DefaultHttpClient client;

    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String ENCODING_GZIP = "gzip";
    private static final String REQ_ENCODING_GZIP = "gzip, deflate";
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static final String HEADER_CONTENT_TYPE = "Content-type";
    private static final String VALUE_CONTENT_TYPE = "application/json";

    static String TAG_REQUEST = "Request";
    static String TAG_RESPONSE = "Response";

    private BasicHttpParams longHttpParams;

    HashMap<String, AsyncTask> mapRequest = new HashMap<>();

    public ServiceConnection() {
        initConnection();
    }

    private void initConnection() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        longHttpParams = new BasicHttpParams();
        initHttpParams(basicHttpParams, TIMEOUT);
        initHttpParams(longHttpParams, TIMEOUT);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory
                .getSocketFactory(), 443));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(
                basicHttpParams, schemeRegistry);
        client = new DefaultHttpClient(threadSafeClientConnManager,
                basicHttpParams);

        client.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(HttpRequest request, HttpContext context) {
                // Add header to accept gzip content
                if (!request.containsHeader(HEADER_ACCEPT_ENCODING)) {
                    request.addHeader(HEADER_ACCEPT_ENCODING, REQ_ENCODING_GZIP);
                }

                // Add header to set Content-Type
                if (!request.containsHeader(HEADER_CONTENT_TYPE)) {
                    request.addHeader(HEADER_CONTENT_TYPE, VALUE_CONTENT_TYPE);
                } else {
                    request.setHeader(HEADER_CONTENT_TYPE, VALUE_CONTENT_TYPE);
                }
            }
        });

        client.addResponseInterceptor(new HttpResponseInterceptor() {
            public void process(HttpResponse response, HttpContext context) {

                if (response.containsHeader(HEADER_CONTENT_ENCODING)) {
                    if (response.getFirstHeader(HEADER_CONTENT_ENCODING)
                            .getValue().equals(ENCODING_GZIP)) {
                        response.setEntity(new InflatingEntity(response
                                .getEntity()));
                    }
                }
            }
        });
    }

    private void initHttpParams(HttpParams params, int timeout) {
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpConnectionParams.setConnectionTimeout(params, timeout);
        HttpConnectionParams.setSoTimeout(params, timeout);
    }

    private void cancelOldRequest(String apiIdentify, AsyncTask task) {
        AsyncTask oldTask = mapRequest.get(apiIdentify);
        if (oldTask != null && oldTask.getStatus() != AsyncTask.Status.FINISHED) {
            oldTask.cancel(true);
        }
        mapRequest.put(apiIdentify, task);
    }



    public void callHttpGetService(RequestObj request, ResponseHandler responseHandler) {
        if (request == null || request.getAPI().length() == 0) {
            return;
        }

        HttpGetService httpGetService = new HttpGetService(responseHandler, request);
        cancelOldRequest(request.getApiIdentify(), httpGetService);
        try {
            httpGetService.executeOnExecutor(
                    AsyncTask.THREAD_POOL_EXECUTOR, "");
        } catch (IllegalStateException e) {
            notifyErrorResponse(responseHandler, ResponseStatus.ConnectionError, request.getApiIdentify());
        }
    }

    public void callHttpPatchService(RequestObj request, ResponseHandler responseHandler) {
        if (request.getAPI().length() == 0) {
            return;
        }
        HttpPatchService httpPatchService = new HttpPatchService(responseHandler, request);
        cancelOldRequest(request.getApiIdentify(), httpPatchService);
        try {
            httpPatchService.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                    "");
        } catch (IllegalStateException e) {
            notifyErrorResponse(responseHandler, ResponseStatus.ConnectionError, request.getApiIdentify());
        }
    }

    public void callHttpPostService(RequestObj request, ResponseHandler responseHandler) {
        if (request.getAPI().length() == 0) {
            return;
        }
        HttpPostService httpPostService = new HttpPostService(responseHandler, request);
        cancelOldRequest(request.getApiIdentify(), httpPostService);
        try {
            httpPostService.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                    "");
        } catch (IllegalStateException e) {
            notifyErrorResponse(responseHandler, ResponseStatus.ConnectionError, request.getApiIdentify());
        }
    }

    public void callHttpDeleteService(RequestObj request, ResponseHandler responseHandler) {
        if (request.getAPI().length() == 0) {
            return;
        }
        HttpDeleteService httpDeleteService = new HttpDeleteService(responseHandler, request);
        try {
            httpDeleteService.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                    "");
        } catch (IllegalStateException e) {
            notifyErrorResponse(responseHandler, ResponseStatus.ConnectionError, request.getApiIdentify());
        }
    }




    class HttpGetService extends AsyncTask<String, String, ResponseObj> {
        RequestObj request;
        ResponseHandler responseHandler;

        public HttpGetService(ResponseHandler responseHandler, RequestObj request) {
            this.responseHandler = responseHandler;
            this.request = request;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected ResponseObj doInBackground(String... params) {
            String result = null;
            try {
                HttpGet get = new HttpGet(request.getAPI());
                Log.i(TAG_REQUEST, request.getApiIdentify() + ":> " + request.getAPI());
                HttpResponse response = ServiceConnection.client.execute(get);
                if (response != null) {
                    result = inputStreamToString(
                            response.getEntity().getContent()).toString();
                    Log.i(TAG_RESPONSE, request.getApiIdentify() + ":> " + result);
                }
            } catch (SocketException e) {
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ResponseObj rObj = genResponseFromContent(request.getApiIdentify(), result);
            return rObj;
        }

        protected void onPostExecute(ResponseObj rObj) {
            super.onPostExecute(rObj);
            if (responseHandler != null) {
                responseHandler.handlerResponseReturned(rObj);
            }
        }
    }

    class HttpPatchService extends AsyncTask<String, String, ResponseObj> {

        RequestObj request;
        ResponseHandler responseHandler;
        HttpParams httpparams;

        public HttpPatchService(ResponseHandler responseHandler, RequestObj request) {
            this.responseHandler = responseHandler;
            this.request = request;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ResponseObj doInBackground(String... params) {


            String result = null;
            try {
                HttpPatch patch = new HttpPatch(request.getAPI());

                Log.i(TAG_REQUEST, request.getApiIdentify() + ":> " + request.getAPI());
                Log.i(TAG_REQUEST, request.getApiIdentify() + ":> " + request.getParameter());

                StringEntity jsonPara = new StringEntity(request.getParameter(), "UTF-8");
                patch.setEntity(jsonPara);
                HttpResponse response = ServiceConnection.client.execute(patch);
                if (response != null) {
                    result = inputStreamToString(
                            response.getEntity().getContent()).toString();
                    Log.i(TAG_RESPONSE, request.getApiIdentify() + ":> " + result);
                }
            } catch (SocketException e) {
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ResponseObj rObj = genResponseFromContent(request.getApiIdentify(), result);
            return rObj;
        }

        protected void onPostExecute(ResponseObj rObj) {
            super.onPostExecute(rObj);
            if (responseHandler != null) {
                responseHandler.handlerResponseReturned(rObj);
            }
        }
    }

    class HttpPostService extends AsyncTask<String, String, ResponseObj> {

        RequestObj request;
        ResponseHandler responseHandler;
        HttpParams httpparams;

        public HttpPostService(ResponseHandler responseHandler, RequestObj request) {
            this.responseHandler = responseHandler;
            this.request = request;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ResponseObj doInBackground(String... params) {


            String result = null;
            try {
                HttpPost post = new HttpPost(request.getAPI());

                Log.i(TAG_REQUEST, request.getApiIdentify() + ":> " + request.getAPI());
                Log.i(TAG_REQUEST, request.getApiIdentify() + ":> " + request.getParameter());

                StringEntity jsonPara = new StringEntity(request.getParameter(), "UTF-8");
                post.setEntity(jsonPara);
                HttpResponse response = ServiceConnection.client.execute(post);
                if (response != null) {
                    result = inputStreamToString(
                            response.getEntity().getContent()).toString();
                    Log.i(TAG_RESPONSE, request.getApiIdentify() + ":> " + result);
                }
            } catch (SocketException e) {
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            ResponseObj rObj = genResponseFromContent(request.getApiIdentify(), result);
            return rObj;
        }

        protected void onPostExecute(ResponseObj rObj) {
            super.onPostExecute(rObj);
            if (responseHandler != null) {
                responseHandler.handlerResponseReturned(rObj);
            }
        }
    }

    class HttpDeleteService extends AsyncTask<String, String, ResponseObj> {

        RequestObj request;
        ResponseHandler responseHandler;
        HttpParams httpparams;

        public HttpDeleteService(ResponseHandler responseHandler, RequestObj request) {
            this.responseHandler = responseHandler;
            this.request = request;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ResponseObj doInBackground(String... params) {


            String result = null;
            HttpDelete delete = new HttpDelete(request.getAPI());
            try {
                Log.i(TAG_REQUEST, request.getApiIdentify() + ":> " + request.getAPI());
                HttpResponse response = ServiceConnection.client.execute(delete);
                if (response != null) {
                    result = inputStreamToString(
                            response.getEntity().getContent()).toString();
                    Log.i(TAG_RESPONSE, request.getApiIdentify() + ":> " + result);
                }
            } catch (SocketException e) {
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ResponseObj rObj = genResponseFromContent(request.getApiIdentify(), result);
            return rObj;
        }

        protected void onPostExecute(ResponseObj rObj) {
            super.onPostExecute(rObj);
            if (responseHandler != null) {
                responseHandler.handlerResponseReturned(rObj);
            }
        }
    }

    private ResponseObj genResponseFromContent(String apiIdentify, String content) {
        ResponseStatus responseStatus;
        JSONObject jsonResponse = null;
        if (content == null) {
            responseStatus = ResponseStatus.ConnectionError;
        } else if (content.length() == 0) {
            responseStatus = ResponseStatus.NoDataError;
        } else {
            try {
                jsonResponse = new JSONObject(content);
                responseStatus = ResponseStatus.SuccessResponse;
            } catch (JSONException e) {
                e.printStackTrace();
                responseStatus = ResponseStatus.JsonFormatError;
            }
        }
        ResponseObj rObj = new ResponseObj(responseStatus, apiIdentify);
        rObj.setData(jsonResponse);
        return rObj;
    }

    private void notifyErrorResponse(ResponseHandler responseHandler, ResponseStatus responseStatus, String apiIdentify) {
        if (responseHandler == null) {
            return;
        }
        ResponseObj response = new ResponseObj(responseStatus, apiIdentify);
        responseHandler.handlerResponseReturned(response);
    }

    public StringBuilder inputStreamToString(InputStream paramInputStream)
            throws IOException {
        StringBuilder localStringBuilder = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(paramInputStream);
        BufferedReader localBufferedReader = new BufferedReader(isr);
        while (true) {
            String str = null;
            try {
                str = localBufferedReader.readLine();
            } catch (OutOfMemoryError e) {

            }
            if (str == null)
                return localStringBuilder;
            localStringBuilder.append(str);
        }
    }

    class InflatingEntity extends HttpEntityWrapper {
        public InflatingEntity(HttpEntity wrapped) {
            super(wrapped);
        }

        @Override
        public InputStream getContent() throws IOException {
            return new GZIPInputStream(wrappedEntity.getContent());
        }

        @Override
        public long getContentLength() {
            return -1;
        }
    }
}
