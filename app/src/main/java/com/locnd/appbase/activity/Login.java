package com.locnd.appbase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.locnd.appbase.ApplicationBase;
import com.locnd.appbase.R;
import com.locnd.appbase.customview.login.EditText_InputEmail;
import com.locnd.appbase.customview.login.EditText_InputName;
import com.locnd.appbase.customview.login.EditText_InputPassword;
import com.locnd.appbase.utils.PrefManager;
import com.locnd.appbase.utils.CommonUtils;
import com.locnd.appbase.utils.ParseUtils;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class Login extends AppCompatActivity {
    public static final String EMAIL_TAG = "email";
    static final int TIME_DELAYED = 2000;
    static final String USERNAME = "123";
    static final String PASSWORD = "12345";

    Toolbar toolbar;
    EditText_InputName edt_inputName;
    EditText_InputEmail edt_inputEmail;
    EditText_InputPassword edt_inputPassword;
    Button btn_login;
    boolean doubleClickToExit = false;
    private PrefManager pref;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(EMAIL_TAG, edt_inputEmail.getEdt_inputemail().getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUtils.verifyParseConfiguration(this);
        pref = new PrefManager(getApplicationContext());
        if (pref.isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.login);
        initView();
        initData();
        CommonUtils.hideKeyBoardWhenClickOutSide(this, findViewById(R.id.ll_login));
        initListener();
    }

    private void login() {
        String email = edt_inputEmail.getEdt_inputemail().getText().toString();
        if (isValidEmail(email)) {
            pref.createLoginSession(email);
            ParseUtils.subscribeWithEmail(pref.getEmail());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid email address!", Toast.LENGTH_LONG).show();
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
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
                    if (edt_inputEmail.validateEmail(Login.this)) {
                        login();
                    } else {
                        actionWhenLoginFailed();
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
