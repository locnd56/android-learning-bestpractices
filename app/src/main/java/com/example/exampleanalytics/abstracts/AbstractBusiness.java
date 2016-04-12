package com.example.exampleanalytics.abstracts;

import com.example.exampleanalytics.R;
import com.example.exampleanalytics.requesttoserver.ResponseHandler;
import com.example.exampleanalytics.requesttoserver.ResponseObj;
import com.google.gson.Gson;

/**
 * Created by Mr.Incredible on 4/12/2016.
 */
public abstract class AbstractBusiness implements ResponseHandler {
    AbstractFragment absFrag;
    public Gson gson = new Gson();

    public AbstractBusiness(AbstractFragment absFrag) {
        this.absFrag = absFrag;
    }

    @Override
    public void handlerResponseReturned(ResponseObj rObj) {
        try {
            notifyReceivedDataDone(rObj);
            switch (rObj.getStatusrCode()) {
                case SuccessResponse:
                    handlerBusiness(rObj);
                    break;
                case ConnectionError:
                    nofityError(absFrag.getString(R.string.msg_nointernetconnection));
                    break;
                case JsonFormatError:
                    nofityError(absFrag.getString(R.string.msg_nointernetconnection));
                    break;
                default:
                    nofityError(absFrag.getString(R.string.msg_unknownerror));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            nofityError("Exception");
        }
    }

    private void nofityError(String msg) {
        absFrag.alertDialog.showMessage(msg);
    }

    protected abstract void handlerBusiness(ResponseObj rObj);

    protected abstract void notifyReceivedDataDone(ResponseObj rObj);
}
