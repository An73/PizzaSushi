package com.dkotenko.pizzasushi.pizzasushi;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public    class CursorBasket extends CursorAdapter {

    private SQLiteDatabase db;
    private AppCompatActivity aContext;
    private TextView sumView;

    public CursorBasket(Context context, Cursor c, SQLiteDatabase db, TextView sumView) {
        super(context, c, 0);
        this.db = db;
        aContext = (AppCompatActivity) context;
        this.sumView = sumView;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_basket_adapter, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameView = (TextView) view.findViewById(R.id.name_order_list);
        TextView costView = (TextView) view.findViewById(R.id.cost_order_list);

        String bd_name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
        String bd_cost = cursor.getString(cursor.getColumnIndexOrThrow("COST"));

        nameView.setText(bd_name);
        costView.setText(bd_cost + "$");
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) aContext.getSystemService(aContext.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_basket_adapter, parent, false);

        Button buttonMinus = (Button) convertView.findViewById(R.id.button_order_list);

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor curs = getCursor();
                curs.moveToPosition(position);
                int idSpeaker = curs.getInt(curs.getColumnIndex("_id"));

                //Log.w("id", String.valueOf(idSpeaker));

                db.delete("BASKET", "_id = ?", new String[] {String.valueOf(idSpeaker)});
                curs.requery();
                notifyDataSetChanged();
                setSum(curs, sumView);
            }
        });
        return super.getView(position, convertView, parent);
    }

    /*Fragment infoFragment = new InfoFragment();

                Bundle arguments = new Bundle();
                Cursor curs = getCursor();
                if (curs.moveToPosition(position)) {
                    arguments.putString(KEY_NAME, curs.getString(curs.getColumnIndexOrThrow("NAME")));
                    arguments.putString(KEY_DESCRIPTION, curs.getString(curs.getColumnIndexOrThrow("DESCRIPTION")));
                    arguments.putString(KEY_COST, curs.getString(curs.getColumnIndexOrThrow("COST")));
                    arguments.putInt(KEY_IMAGE_ID, curs.getInt(curs.getColumnIndexOrThrow("IMAGE_RESOURCE_ID")));
                    infoFragment.setArguments(arguments);

                    FragmentTransaction ft = aContext.getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, infoFragment);
                    ft.addToBackStack(null);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }*/

    private void setSum(Cursor mCurs, TextView sumView) {
        String cost;
        int sum = 0;

        mCurs.moveToFirst();
        while (!mCurs.isAfterLast()) {
            cost = mCurs.getString(mCurs.getColumnIndexOrThrow("COST"));
            sum += Integer.parseInt(cost);
            mCurs.moveToNext();
        }

        sumView.setText("Сумма: " + Integer.toString(sum) + "$");
    }
}
