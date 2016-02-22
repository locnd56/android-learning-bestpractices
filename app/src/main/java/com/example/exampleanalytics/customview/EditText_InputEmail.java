package com.example.exampleanalytics.customview;

import android.app.Service;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.exampleanalytics.R;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class EditText_InputEmail extends LinearLayout {
    TextInputLayout text_inputlayout_email;
    EditText edt_inputemail;

    public EditText_InputEmail(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
//        initData(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.edt_inputemail, this);
        edt_inputemail = (EditText) findViewById(R.id.edt_inputemail);
        text_inputlayout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
    }

    private void initData(Context context) {
        validateEmail(context);
    }

    public boolean validateEmail(Context context) {
        String email = edt_inputemail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            edt_inputemail.setError(context.getString(R.string.err_msg_email));
            edt_inputemail.requestFocus();
            return false;
        } else {
            text_inputlayout_email.setErrorEnabled(false);
        }

        return true;
    }

    public EditText getEdt_inputemail() {
        return edt_inputemail;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
