package com.example.exampleanalytics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.exampleanalytics.customview.login.EditText_InputEmail;
import com.example.exampleanalytics.customview.login.EditText_InputName;
import com.example.exampleanalytics.customview.login.EditText_InputPassword;
import com.example.exampleanalytics.utils.Utils;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class Login extends AppCompatActivity {
    static final int TIME_DELAYED = 2000;
    static final String USERNAME = "LocNguyen";
    static final String PASSWORD = "12345";

    Toolbar toolbar;
    EditText_InputName edt_inputName;
    EditText_InputEmail edt_inputEmail;
    EditText_InputPassword edt_inputPassword;
    Button btn_login;
    boolean doubleClickToExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        initData();
        Utils.hideKeyBoardWhenClickOutSide(this, findViewById(R.id.ll_login));
        initListener();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(R.string.login);
        edt_inputName = (EditText_InputName) findViewById(R.id.edt_login_inputname);
        edt_inputPassword = (EditText_InputPassword) findViewById(R.id.edt_login_inputpassword);
        edt_inputEmail = (EditText_InputEmail) findViewById(R.id.edt_login_inputemail);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    private void initData() {

    }

    private void initListener() {
        try {
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edt_inputEmail.validateEmail(Login.this) && edt_inputName.validateName(Login.this) && edt_inputPassword.validatePassword(Login.this)) {
                        if (edt_inputName.getEdt_inputname().getText().toString().equals(USERNAME) && edt_inputPassword.getEdt_inputpassword().getText().toString().equals(PASSWORD)) {
                            actionWhenLoginSuccessful();
                        } else {
                            actionWhenLoginFailed();
                        }
                    }
                }
            });
        } catch (Exception e) {
            ApplicationBase.getInstance().trackException(e);
        }
    }

    private void actionWhenLoginFailed() {
        Snackbar.make(btn_login, "Login failed!", Snackbar.LENGTH_SHORT).setAction("", null).show();
    }

    private void actionWhenLoginSuccessful() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (doubleClickToExit) {
            super.onBackPressed();
            return;
        }
        Toast.makeText(this, "Click again to exit app", Toast.LENGTH_SHORT).show();
        doubleClickToExit = true;
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleClickToExit = false;
            }
        }, TIME_DELAYED);
    }
}
