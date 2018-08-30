package com.dkotenko.pizzasushi.pizzasushi;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView drawerList;
    private Presenter mPresenter;

    private ActionBarDrawerToggle mBarDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            mPresenter.selectItem(position, getFragmentManager().beginTransaction(),
                    (DrawerLayout)findViewById(R.id.drawer_layout), drawerList);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mPresenter = new Presenter(this);
        if (savedInstanceState == null) {
            mPresenter.startMain(getFragmentManager().beginTransaction());
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        drawerList = (ListView) findViewById(R.id.drawer);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        mPresenter.setAdapter(drawerList, getResources().getStringArray(R.array.titles), this);
        mPresenter.setHeader(getLayoutInflater().inflate(R.layout.drawer_header, null), drawerList);


        mBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu();
                syncState();
            }
        };
        mDrawerLayout.setDrawerListener(mBarDrawerToggle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("NONULL", 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
