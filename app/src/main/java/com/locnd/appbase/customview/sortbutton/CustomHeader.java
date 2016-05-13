package com.locnd.appbase.customview.sortbutton;

import android.app.Service;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.locnd.appbase.R;

/**
 * Created by Mr.Incredible on 3/23/2016.
 */
public class CustomHeader extends LinearLayout {

    public enum STATE_BSGAIN {
        STATE_ONE(1), STATE_TWO(2), STATE_THREE(3);
        int value;

        STATE_BSGAIN(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    RelativeLayout rl_header;
    TextView tv_text;
    public int stateChange;

    String header;
    String textChange1;
    String textChange2;
    String textChange3;

    ImageView iv_sortheader_sort;

    OnChangeFirstTurnListener onChangeFirstTurnListener;
    OnChangeSecondTurnListener onChangeSecondTurnListener;
    OnChangeThirdTurnListener onChangeThirdTurnListener;

    public CustomHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initStateChange(context, attrs);
        initListener();
    }

    private void initListener() {
        rl_header.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stateChange == STATE_BSGAIN.STATE_ONE.getValue()) {
                    stateChange = STATE_BSGAIN.STATE_TWO.getValue();
                    tv_text.setText(getTextChange2());
                    onChangeFirstTurnListener.onChangeFirst();
                } else if (stateChange == STATE_BSGAIN.STATE_TWO.getValue()) {
                    stateChange = STATE_BSGAIN.STATE_THREE.getValue();
                    tv_text.setText(getTextChange3());
                    onChangeSecondTurnListener.onChangeSecond();
                } else if (stateChange == STATE_BSGAIN.STATE_THREE.getValue()) {
                    stateChange = STATE_BSGAIN.STATE_ONE.getValue();
                    tv_text.setText(getTextChange1());
                    onChangeThirdTurnListener.onChangeThird();
                }
            }
        });
    }

    private void initStateChange(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Header_Text,
                0, 0);
        String mHeaderText = typedArray.getString(R.styleable.Header_Text_text_header);
        stateChange = STATE_BSGAIN.STATE_ONE.getValue();
        tv_text.setText(mHeaderText);
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.sortheader, this);
        rl_header = (RelativeLayout) findViewById(R.id.ll_sortheader);
        tv_text = (TextView) findViewById(R.id.tv_sortheader_text);
        iv_sortheader_sort = (ImageView) findViewById(R.id.iv_sortheader_sorticon);

    }

    public interface OnChangeFirstTurnListener {
        public void onChangeFirst();
    }

    public interface OnChangeSecondTurnListener {
        public void onChangeSecond();
    }

    public interface OnChangeThirdTurnListener {
        public void onChangeThird();
    }

    public void setOnChangeFirstTurnListener(OnChangeFirstTurnListener onChangeFirstTurnListener) {
        this.onChangeFirstTurnListener = onChangeFirstTurnListener;
    }

    public void setOnChangeSecondTurnListener(OnChangeSecondTurnListener onChangeSecondTurnListener) {
        this.onChangeSecondTurnListener = onChangeSecondTurnListener;
    }

    public void setOnChangeThirdTurnListener(OnChangeThirdTurnListener onChangeThirdTurnListener) {
        this.onChangeThirdTurnListener = onChangeThirdTurnListener;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTextChange1() {
        return textChange1;
    }

    public void setTextChange1(String textChange1) {
        this.textChange1 = textChange1;
    }

    public String getTextChange2() {
        return textChange2;
    }

    public void setTextChange2(String textChange2) {
        this.textChange2 = textChange2;
    }

    public String getTextChange3() {
        return textChange3;
    }

    public void setTextChange3(String textChange3) {
        this.textChange3 = textChange3;
    }
}
