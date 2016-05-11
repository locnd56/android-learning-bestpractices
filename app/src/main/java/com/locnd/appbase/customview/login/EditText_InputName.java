package com.locnd.appbase.customview.login;

import android.app.Service;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.locnd.appbase.R;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class EditText_InputName extends LinearLayout {
    TextInputLayout text_inputlayout_name;
    EditText edt_inputname;

    public EditText_InputName(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
//        initData(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.edt_inputname, this);
        text_inputlayout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        edt_inputname = (EditText) findViewById(R.id.edt_inputname);
    }

    public void initData(Context context) {
        validateName(context);
    }

    public boolean validateName(Context context) {
        if (edt_inputname.getText().toString().trim().isEmpty()) {
            text_inputlayout_name.setError(context.getString(R.string.err_msg_name));
            edt_inputname.requestFocus();
            return false;
        } else {
            text_inputlayout_name.setErrorEnabled(false);
        }

        return true;
    }

    public EditText getEdt_inputname() {
        return edt_inputname;
    }
}
