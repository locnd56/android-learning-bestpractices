package com.locnd.appbase;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.locnd.appbase.abstracts.AbstractFragment;
import com.locnd.appbase.customview.actionbar.TabActionBarLayout;
import com.locnd.appbase.customview.viewpager.ViewPagerCustom;
import com.locnd.appbase.customview.viewpager.ViewPagerFragmentAdapter;
import com.locnd.appbase.customview.viewpager.ViewPagerManager;
import com.locnd.appbase.fragment.homefragment.HomeFragment;
import com.locnd.appbase.fragment.nav_slidemenu.SlideMenuFragment;
import com.locnd.appbase.model.TabItem;
import com.locnd.appbase.utils.CommonUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String DEBUG_TAG = this.getClass().getSimpleName();

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    private SlideMenuFragment slideMenu;
    FrameLayout fl_container;
    AbstractFragment currentFrag;
    private boolean doubleBackToExitPressedOnce = false;
    private final static int TIME_WAITING_EXIT = 2000;
    List<TabItem> tabItemList;
    ViewPagerCustom viewPager;

    public TabActionBarLayout tabHome;
    public TabActionBarLayout tabFriends;
    public TabActionBarLayout tabMessages;
    public TabActionBarLayout tabLearning;
    ViewPagerManager screenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonUtils.hideKeyBoardWhenClickOutSide(this, findViewById(R.id.ll_activitymain));
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
    }

    private void setUpViewPager(ViewPager viewPager) {
        if (screenManager == null) {
            screenManager = new ViewPagerManager(this);
        }
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(screenManager);
        viewPager.setAdapter(adapter);
    }

    private void initListener() {
        slideMenu.setDrawerListener(new SlideMenuFragment.FragmentDrawerListener() {
            @Override
            public void onDrawerItemSelected(View view, int position, String clazz) {
                displayFragment(clazz);
            }
        });
        tabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveTabActionbar(v);
            }
        });
        tabMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveTabActionbar(v);
            }
        });
        tabFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveTabActionbar(v);
            }
        });
        tabLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveTabActionbar(v);
            }
        });
    }

    private void resetActived() {
        tabHome.setActivated(false);
        tabFriends.setActivated(false);
        tabLearning.setActivated(false);
        tabMessages.setActivated(false);
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
        getSupportActionBar().setTitle(frag.getTitle(this));
        if (frag != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fl_container, frag);
            fragmentTransaction.commit();
        }
    }

    private void initView() {
        fl_container = (FrameLayout) findViewById(R.id.fl_container);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        slideMenu = (SlideMenuFragment) getSupportFragmentManager().findFragmentById(R.id.slidemenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        slideMenu.setUp(R.id.slidemenu, drawerLayout, toolbar);
        viewPager = (ViewPagerCustom) findViewById(R.id.viewPager);

        tabHome = (TabActionBarLayout) findViewById(R.id.tabHome);
        tabFriends = (TabActionBarLayout) findViewById(R.id.tabFriends);
        tabMessages = (TabActionBarLayout) findViewById(R.id.tabMessages);
        tabLearning = (TabActionBarLayout) findViewById(R.id.tabLearning);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewPager != null) {
            viewPager.setCurrentItem(0);
        }
        ApplicationBase.getInstance().trackScreen(this.getClass().getName());
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
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewAtSearchOpen(ActionBar action) {
        action.setDisplayShowCustomEnabled(true);
        action.setCustomView(R.layout.search_bar);
        action.setDisplayShowTitleEnabled(false);
    }

    private void setViewAtSearchClosed(ActionBar action) {
        action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
        action.setDisplayShowTitleEnabled(true); //show the title in the action bar
        CommonUtils.hideSoftKeyboard(this);
    }

    private void doSearch() {

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                Log.d(DEBUG_TAG, "Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE):
                Log.d(DEBUG_TAG, "Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP):
                Log.d(DEBUG_TAG, "Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(DEBUG_TAG, "Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(DEBUG_TAG, "Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    public void setActiveTabActionbar(View v) {
        resetActived();
        if (v != null) {
            v.setActivated(true);
        }
    }

}
