package com.locnd.appbase.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.locnd.appbase.MainActivity;
import com.locnd.appbase.model.TabItem;

import java.util.List;

/**
 * Created by Mr.Incredible on 2/25/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<TabItem> tabItemList;
    FragmentManager fm;

    public ViewPagerAdapter(FragmentManager fm, List<TabItem> tabItemList) {
        super(fm);
        this.tabItemList = tabItemList;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return tabItemList.get(position).getFragment();
            case 1:
                return tabItemList.get(position).getFragment();
            case 2:
                return tabItemList.get(position).getFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return MainActivity.COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return POSITION_NONE;
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        if (position >= getCount()) {
//            FragmentManager manager = ((Fragment) object).getFragmentManager();
//            FragmentTransaction trans = manager.beginTransaction();
//            trans.remove((Fragment) object);
//            trans.commit();
//        }
//    }
}
