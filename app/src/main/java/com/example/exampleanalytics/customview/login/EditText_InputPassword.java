package com.example.exampleanalytics.customview.login;

import android.app.Service;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.exampleanalytics.R;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class EditText_InputPassword extends LinearLayout {
    TextInputLayout text_inputlayout_password;
    EditText edt_inputpassword;

    public EditText_InputPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
//        initData(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.edt_inputpassword, this);
        edt_inputpassword = (EditText) findViewById(R.id.edt_inputpassword);
        text_inputlayout_password = (TextInputLayout) findViewById(R.id.input_layout_password);
    }

    private void initData(Context context) {
        validatePassword(context);
    }

    public boolean validatePassword(Context context) {
        if (edt_inputpassword.getText().toString().trim().isEmpty()) {
            text_inputlayout_password.setError(context.getString(R.string.err_msg_password));
            edt_inputpassword.requestFocus();
            return false;
        } else {
            text_inputlayout_password.setErrorEnabled(false);
        }

        return true;
    }

    public EditText getEdt_inputpassword() {
        return edt_inputpassword;
    }

}
