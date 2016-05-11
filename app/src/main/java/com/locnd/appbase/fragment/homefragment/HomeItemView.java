package com.locnd.appbase.fragment.homefragment;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.locnd.appbase.R;
import com.locnd.appbase.abstracts.AbstractItemView;

/**
 * Created by Mr.Incredible on 4/19/2016.
 */
public class HomeItemView extends AbstractItemView {

    TextView item1;
    TextView item2;
    TextView item3;
    TextView item4;


    public HomeItemView(Context context) {
        super(context);
    }

    @Override
    public void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.homeitemview, this);
        item1 = (TextView) findViewById(R.id.tv_homeitemview_item1);
        item2 = (TextView) findViewById(R.id.tv_homeitemview_item2);
        item3 = (TextView) findViewById(R.id.tv_homeitemview_item3);
        item4 = (TextView) findViewById(R.id.tv_homeitemview_item4);
    }

    @Override
    public void setData(Object obj) {
        if (obj != null && obj instanceof HomeItem) {
            item1.setText(((HomeItem) obj).getItem1());
            item2.setText(((HomeItem) obj).getItem2());
            item3.setText(((HomeItem) obj).getItem3());
            item4.setText(((HomeItem) obj).getItem4());
        }
    }

}
