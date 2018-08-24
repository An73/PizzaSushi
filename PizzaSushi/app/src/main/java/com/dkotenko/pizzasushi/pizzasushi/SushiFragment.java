package com.dkotenko.pizzasushi.pizzasushi;

import android.app.Fragment;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public    class SushiFragment extends ListFragment {

    private SQLiteDatabase db;
    private Cursor mCursor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        SQLiteOpenHelper sqLiteOpenHelper = new BDHelper(inflater.getContext());
        db = sqLiteOpenHelper.getReadableDatabase();
        mCursor = db.query("PIZZA", new String[]{"NAME"},
                null, null, null, null, null);


        String[] array = new String[1];
        array[0] = "0";
        if (mCursor.moveToFirst()) {
           // String uname = mCursor.getString(0);
            array[0] = "21321";
        }
        //String uname = "231213";
        /*for(int i = 0; mCursor.moveToNext(); i++) {
            String uname = mCursor.getString(mCursor.getColumnIndex("NAME"));
            array[i] = uname;
        }*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                /*getResources().getStringArray(R.array.dp_pizza_cost)*/array);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
