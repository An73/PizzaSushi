package com.dkotenko.pizzasushi.pizzasushi;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public    class MyCursorAdapter extends CursorAdapter {
    final static String KEY_NAME = "key_name";
    final static String KEY_DESCRIPTION = "key_description";
    final static String KEY_COST = "key_cost";
    final static String KEY_IMAGE_ID = "key_image_id";

    private ImageButton imageButtonInfo;
    private AppCompatActivity aContext;

    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        aContext = (AppCompatActivity) context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_my_adapter, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.name_view);
        TextView cost = (TextView) view.findViewById(R.id.cost_view);
        ImageView imgView = (ImageView) view.findViewById(R.id.image_view);

        String bd_name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
        String bd_cost = cursor.getString(cursor.getColumnIndexOrThrow("COST"));
        int img = cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE_RESOURCE_ID"));

        name.setText(bd_name);
        cost.setText("Стоимость " + bd_cost);
        imgView.setImageResource(img);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) aContext.getSystemService(aContext.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_my_adapter, parent, false);

        imageButtonInfo = (ImageButton) convertView.findViewById(R.id.info_view);

        imageButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment infoFragment = new InfoFragment();

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
                }
            }
        });


        return super.getView(position, convertView, parent);
    }
}
