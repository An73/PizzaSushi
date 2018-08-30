package com.dkotenko.pizzasushi.pizzasushi;

import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

public    class DrinkFragment extends ListFragment {

    private SQLiteDatabase db;
    private Cursor mCursor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteOpenHelper sqLiteOpenHelper = new BDHelper(getActivity());
        db = sqLiteOpenHelper.getWritableDatabase();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Напитки");

        mCursor = db.query("DRINK", new String[]{"_id", "NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "COST", "TO_BASKET"},
                null, null, null, null, null);
        MyCursorAdapter myCursorAdapter = new MyCursorAdapter(getActivity(), mCursor, db, "DRINK");
        setListAdapter(myCursorAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
