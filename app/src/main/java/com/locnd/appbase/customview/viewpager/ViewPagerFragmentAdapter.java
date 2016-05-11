package com.locnd.appbase.customview.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.locnd.appbase.abstracts.AbstractFragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Incredible on 5/11/2016.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    public static String[] listFragmentNames = {"HomeFragment", "FriendsFragment", "MessagesFragment", "LearningFragment"};
    TabActionBarItem tabHomeFragment = new TabActionBarItem(listFragmentNames[0], 0);
    TabActionBarItem tabFriendsFragment = new TabActionBarItem(listFragmentNames[1], 1);
    TabActionBarItem tabMessagesFragment = new TabActionBarItem(listFragmentNames[2], 2);
    TabActionBarItem tabLearningFragment = new TabActionBarItem(listFragmentNames[3], 3);
    List<TabActionBarItem> itemList = new ArrayList<>();

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        if (itemList != null) {
            itemList.clear();
            itemList.add(0, tabHomeFragment);
            itemList.add(1, tabFriendsFragment);
            itemList.add(2, tabMessagesFragment);
            itemList.add(3, tabLearningFragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return initFragment(itemList.get(position).getClassName());
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    private AbstractFragment initFragment(String className) {
        Class<?> clazz = null;
        if (className != null) {
            try {
                clazz = Class.forName(className);
                Constructor<?> ctor = clazz.getConstructor();
                Object object = ctor.newInstance();
                if (object instanceof AbstractFragment) {
                    AbstractFragment fragment = (AbstractFragment) object;
                    return fragment;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

}
