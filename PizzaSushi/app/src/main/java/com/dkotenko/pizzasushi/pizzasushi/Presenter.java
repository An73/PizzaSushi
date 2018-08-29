package com.dkotenko.pizzasushi.pizzasushi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public    class Presenter   {

    private BDHelper mBDHelper;
    private PizzaFragment mPizzaFragment;
    private Context pContext;


    Presenter(Context context) {
        mBDHelper = new BDHelper(context);
        pContext = context;
    }


    public void selectItem(int position, FragmentTransaction ft, DrawerLayout drawerLayout, ListView drawerList) {
        Fragment listFragment;
        switch (position) {
            case 1:
                listFragment = new PizzaFragment();
                break;
            case 2:
                listFragment = new SushiFragment();
                break;
            case 3:
                listFragment = new SushiFragment();
                break;
            case 4:
                listFragment = new BasketFragment();
                break;
            default:
                listFragment = new PizzaFragment();
        }
        ft.replace(R.id.content_frame, listFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        drawerLayout.closeDrawer(drawerList);
    }

    public void startMain(FragmentTransaction ft) {
        Fragment listFragment = new PizzaFragment();

        ft.replace(R.id.content_frame, listFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }



    public void setAdapter(ListView drawerList, String[] titles, Context context) {
        drawerList.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_activated_1, titles) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                tv.setGravity(Gravity.CENTER);

                return view;
            }
        });
    }

    public void setHeader(View listHeader, ListView drawerList) {
        drawerList.addHeaderView(listHeader);
    }

}
