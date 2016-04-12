package com.example.exampleanalytics.customview.alertdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.g.customwidget.R;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.SimpleAction;

public class AlertDialog extends Dialog {
    Context context;
    TextView tv_message;
    TextView tv_Title;
    TextView tv_Positive;
    TextView tv_Negative;

    SimpleAction simpleAction;

    OnDismissListener onDismissListener = new OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            if (simpleAction != null) {
                simpleAction.performAction(null);
            }
            simpleAction = null;
            tv_Negative.setVisibility(View.GONE);
            tv_Positive.setText(context.getString(R.string.XacNhan));
        }
    };

    public AlertDialog(Context context) {
        super(context);
        this.context = context;
        setCancelable(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alertdialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
        getWindow().setBackgroundDrawableResource(R.color.alertdialog_background_color);

        tv_message = (TextView) findViewById(R.id.text_alertdialog_message);
        tv_Title = (TextView) findViewById(R.id.text_alertdialog_title);
        tv_Positive = (TextView) findViewById(R.id.text_alertdialog_possitive);
        tv_Negative = (TextView) findViewById(R.id.text_alertdialog_negative);

        tv_Positive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_Negative.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                simpleAction = null;
                dismiss();
            }
        });
        setOnDismissListener(onDismissListener);
    }


    public void showMessage(String title, String msg) {
        showDialog(title, msg, null);
    }

    public void showMessage(String title, String msg,
                            SimpleAction simpleAction) {
        showDialog(title, msg, simpleAction);

    }

    public void showQuestion(String title, String msg, SimpleAction simpleAction) {
        tv_Negative.setVisibility(View.VISIBLE);
        tv_Positive.setText(context.getString(R.string.Co));
        tv_Negative.setText(context.getString(R.string.Khong));
        showDialog(title, msg, simpleAction);
    }

    public void showDialog(String title, String msg, SimpleAction simpleAction) {
        dismiss();
        if (title != null) {
            tv_Title.setText(title);
        }
        if (msg != null) {
            tv_message.setText(msg);
        }
        this.simpleAction = simpleAction;
        show();

    }

    public void setPositiveButtonText(String positiveText) {
        tv_Positive.setText(positiveText);
    }

    public void setNegativeButtonText(String positiveText) {
        tv_Negative.setText(positiveText);
    }

    public void setPositiveButtonAction(final SimpleAction action) {
        tv_Positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null) {
                    action.performAction(null);
                }
            }
        });
    }

    public void setNegativeButtonAction(final SimpleAction action) {
        tv_Negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null) {
                    action.performAction(null);
                }
            }
        });
    }

    @Override
    public void setTitle(CharSequence title) {
        tv_Title.setText(title);
    }

    public void setMessage(String msg) {
        tv_message.setText(msg);
    }
}
