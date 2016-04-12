package com.example.exampleanalytics.fragment.friendsfragment;

import android.util.Log;

import com.example.exampleanalytics.ApplicationData;
import com.example.exampleanalytics.abstracts.AbstractBusiness;
import com.example.exampleanalytics.apilist.APIList;
import com.example.exampleanalytics.model.ContactItem;
import com.example.exampleanalytics.requesttoserver.RequestObj;
import com.example.exampleanalytics.requesttoserver.ResponseObj;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Incredible on 4/12/2016.
 */
public class FriendsBusiness extends AbstractBusiness {
    FriendsFragment fragment;
    List<ContactItem> items = new ArrayList<>();

    public FriendsBusiness(FriendsFragment absFrag) {
        super(absFrag);
        this.fragment = absFrag;
    }

    public void callGetData() {
        RequestObj requestObj = new RequestObj(APIList.androidhiveAPI, "ANDROID");
        ApplicationData.serviceConnection.callHttpGetService(requestObj, this);

//        RequestObj requestObj = new RequestObj(APIList.LoginAPI, APIList.KEY_LOGIN);
//        String paramsLogin = APIList.formatParams(APIList.LoginAPIParam, "TAMNT", "123456");
//        requestObj.setParameter(paramsLogin);
//        ApplicationData.serviceConnection.callHttpPostService(requestObj, this);
    }

    @Override
    protected void handlerBusiness(ResponseObj rObj) {
        switch (rObj.getApiIdentify()) {
            case "ANDROID":
                Log.e("1", rObj.getApiIdentify());
                Log.e("2", rObj.getData() + "");
                Log.e("3", rObj.getStatusrCode() + "");
                Log.e("4", rObj.getInfo("contacts") + "");
                try {
                    JSONArray contacts = rObj.getData().getJSONArray("contacts");
                    for (int i = 0; i < contacts.length(); i++) {
                        ContactItem item = gson.fromJson(String.valueOf(contacts.getJSONObject(i)), ContactItem.class);
                        items.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                fragment.setContent(items.get(0).getId());
                break;
        }
    }

    @Override
    protected void notifyReceivedDataDone(ResponseObj rObj) {

    }
}
