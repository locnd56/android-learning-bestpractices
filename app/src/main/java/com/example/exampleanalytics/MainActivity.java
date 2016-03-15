package com.example.exampleanalytics;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exampleanalytics.abstracts.AbstractFragment;
import com.example.exampleanalytics.adapter.ViewPagerAdapter;
import com.example.exampleanalytics.fragment.friends.FriendsFragment;
import com.example.exampleanalytics.fragment.homefragment.HomeFragment;
import com.example.exampleanalytics.fragment.messagesfragment.MessagesFragment;
import com.example.exampleanalytics.fragment.nav_slidemenu.SlideMenuFragment;
import com.example.exampleanalytics.fragment.nav_slidemenu.SlideMenuItem;
import com.example.exampleanalytics.model.TabItem;
import com.example.exampleanalytics.parser.XMLParser;
import com.example.exampleanalytics.utils.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int COUNT = 3;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    private SlideMenuFragment slideMenu;
    FrameLayout fl_container;
    AbstractFragment currentFrag;
    private boolean doubleBackToExitPressedOnce = false;
    private final static int TIME_WAITING_EXIT = 2000;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;
    List<TabItem> tabItemList;
    List<SlideMenuItem> slideMenuItemList;
    String declareTabViewPager = "#" + "Home" + "#" + "Friends" + "#" + "Messages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.hideKeyBoardWhenClickOutSide(this, findViewById(R.id.ll_activitymain));
        initView();
        initData();
        initListener();

    }

    private void initData() {
        currentFrag = initFragment(HomeFragment.class.getName());
        if (tabItemList == null) {
            tabItemList = new ArrayList<>();
        }
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setUpTabIcons();
    }

    private void setUpTabIcons() {
        for (int i = 0; i < COUNT; i++) {
            String iconPath = slideMenuItemList.get(i).getIcon();
            if (iconPath != null && iconPath.length() > 0) {
                int icon = getResources().getIdentifier(iconPath, "mipmap", this.getPackageName());
                tabLayout.getTabAt(i).setIcon(icon);
            }
        }
    }

    private void setUpViewPager(ViewPager viewPager) {
        slideMenuItemList = new ArrayList<>();
        slideMenuItemList = XMLParser.parseXML(getResources().getXml(R.xml.declare_slidemenu));
        for (int i = 0; i < COUNT; i++) {
            SlideMenuItem slideMenuItem = slideMenuItemList.get(i);
            TabItem item = new TabItem(initFragment(slideMenuItem.getClassName_str()), slideMenuItem.getIcon());
            tabItemList.add(item);
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabItemList);
        viewPager.setAdapter(adapter);
    }

    private void initListener() {
        slideMenu.setDrawerListener(new SlideMenuFragment.FragmentDrawerListener() {
            @Override
            public void onDrawerItemSelected(View view, int position, String clazz) {
                displayFragment(clazz);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                displayFragment(tabItemList.get(position).getFragment().getClass().getName());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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

    private void displayFragment(String className) {
        AbstractFragment frag = initFragment(className);
        currentFrag = frag;
        if (className.equals(HomeFragment.class.getName())) {
            viewPager.setVisibility(View.VISIBLE);
            viewPager.setCurrentItem(0);
        } else if (className.equals(FriendsFragment.class.getName())) {
            viewPager.setVisibility(View.VISIBLE);
            viewPager.setCurrentItem(1);
        } else if (className.equals(MessagesFragment.class.getName())) {
            viewPager.setVisibility(View.VISIBLE);
            viewPager.setCurrentItem(2);
        } else {
            viewPager.setVisibility(View.GONE);
            getSupportActionBar().setTitle(frag.getTitle(this));
            if (frag != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_container, frag);
                fragmentTransaction.commit();

            }
        }
    }

    private void initView() {
        fl_container = (FrameLayout) findViewById(R.id.fl_container);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        slideMenu = (SlideMenuFragment) getSupportFragmentManager().findFragmentById(R.id.slidemenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        slideMenu.setUp(R.id.slidemenu, drawerLayout, toolbar);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomApplication.getInstance().trackScreen(this.getClass().getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_search) {
            hanleMenuSearch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hanleMenuSearch() {
        ActionBar action = getSupportActionBar();
        if (isSearchOpened) {
            setViewAtSearchClosed(action);
        } else {
            setViewAtSearchOpen(action);
        }
    }

    private void setViewAtSearchOpen(ActionBar action) {
        action.setDisplayShowCustomEnabled(true);
        action.setCustomView(R.layout.search_bar);
        action.setDisplayShowTitleEnabled(false);
        edtSeach = (EditText) action.getCustomView().findViewById(R.id.edt_searchbar);
        edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    doSearch();
                    return true;
                }
                return false;
            }
        });
        edtSeach.requestFocus();
        Utils.showSoftKeyBoard(this);
        mSearchAction.setIcon(getResources().getDrawable(R.mipmap.ic_search));
        isSearchOpened = true;
    }

    private void setViewAtSearchClosed(ActionBar action) {
        action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
        action.setDisplayShowTitleEnabled(true); //show the title in the action bar
        Utils.hideSoftKeyboard(this);
        mSearchAction.setIcon(getResources().getDrawable(R.mipmap.ic_search));
        isSearchOpened = false;
    }

    private void doSearch() {

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (isSearchOpened) {
            hanleMenuSearch();
            return;
        }
        doubleBacktoExit();
    }

    private void doubleBacktoExit() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, TIME_WAITING_EXIT);
    }
}
