package com.locnd.appbase.fragment.learningfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.locnd.appbase.ApplicationData;
import com.locnd.appbase.R;
import com.locnd.appbase.abstracts.AbstractFragment;
import com.locnd.appbase.serverconnection.IResponseResult;
import com.locnd.appbase.serverconnection.RequestObject;
import com.locnd.appbase.serverconnection.ResponseObject;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * Created by Mr.Incredible on 2/25/2016.
 */
public class LearningFragment extends AbstractFragment implements IResponseResult {
    public final String KEY = "LEARNINGFRAGMENT";
    TextView tv_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_content = (TextView) view.findViewById(R.id.tv_learningfragment_content);
    }

    @Override
    public void onResume() {
        super.onResume();
        showToast(getMainActivity(), "Learning created");
        callGetData();
    }

    private void callGetData() {
        String api = "http://192.168.1.102/FDSMflexApps/MAccount/LoginF?";
        RequestParams params = new RequestParams();
        params.put("username", "009C299999");
        params.put("password", "123");
        RequestObject requestObj = new RequestObject(api, params, KEY);
        ApplicationData.connectionData.callPostHttpRequest(requestObj, this);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_learning);
    }

    @Override
    public void response(ResponseObject obj) {
        switch (obj.getKeyApiIdentifier()) {
            case KEY:
                if (obj != null) {
                    Log.e("learningfragment", obj.getStatusCode() + "");
                    if (obj.getData() instanceof JSONObject) {
                        JSONObject object = (JSONObject) obj.getData();
                    }
                }
                break;
            default:
                break;
        }

    }
}
