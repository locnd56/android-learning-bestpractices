package com.example.exampleanalytics;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.exampleanalytics.abstracts.AbstractFragment;
import com.example.exampleanalytics.fragment.FragmentDrawerSlideMenu;
import com.example.exampleanalytics.fragment.FriendsFragment;
import com.example.exampleanalytics.fragment.HomeFragment;
import com.example.exampleanalytics.fragment.MessagesFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    private FragmentDrawerSlideMenu slideMenu;
    FrameLayout fl_container;
    AbstractFragment abs_fragment;
    private boolean doubleBackToExitPressedOnce = false;
    private final static int TIME_WAITING_EXIT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

    }

    private void initListener() {
        slideMenu.setDrawerListener(new FragmentDrawerSlideMenu.FragmentDrawerListener() {
            @Override
            public void onDrawerItemSelected(View view, int position) {
                displayFragmentSlideMenu(position);
            }
        });
    }

    private void displayFragmentSlideMenu(int position) {
        switch (position) {
            case 0:
                abs_fragment = new HomeFragment();
                getSupportActionBar().setTitle(abs_fragment.getTitle(this));
                break;
            case 1:
                abs_fragment = new FriendsFragment();
                getSupportActionBar().setTitle(abs_fragment.getTitle(this));
                break;
            case 2:
                abs_fragment = new MessagesFragment();
                getSupportActionBar().setTitle(abs_fragment.getTitle(this));
                break;
            default:
                break;
        }
        displayFragment(abs_fragment);

    }

    private void displayFragment(AbstractFragment abs_fragment) {
        if (abs_fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fl_container, abs_fragment);
            fragmentTransaction.commit();

        }
    }

    private void initView() {
        fl_container = (FrameLayout) findViewById(R.id.fl_container);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        slideMenu = (FragmentDrawerSlideMenu) getSupportFragmentManager().findFragmentById(R.id.slidemenu);
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
        }
        return super.onOptionsItemSelected(item);
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
}
