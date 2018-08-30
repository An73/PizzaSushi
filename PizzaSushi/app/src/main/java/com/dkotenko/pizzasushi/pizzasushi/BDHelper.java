package com.dkotenko.pizzasushi.pizzasushi;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public    class BDHelper extends SQLiteOpenHelper {

    private final Context fContext;
    private static final String DB_NAME = "pizzasushi";
    private static final int DB_VERSION = 1;

    BDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        fContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        Resources res = fContext.getResources();
        String[] name_strings = res.getStringArray(R.array.db_pizza_name);
        String[] cost_strings = res.getStringArray(R.array.dp_pizza_cost);
        String[] descrption_strings = res.getStringArray(R.array.db_pizza_description);


        db.execSQL("CREATE TABLE PIZZA ("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "NAME TEXT, "
        + "COST TEXT, "
        + "DESCRIPTION TEXT, "
        + "TO_BASKET INTEGER, "
        + "IMAGE_RESOURCE_ID INTEGER);");

        for (int i = 0; i < name_strings.length; i++) {
            values.put("NAME", name_strings[i]);
            values.put("COST", cost_strings[i]);
            values.put("DESCRIPTION", descrption_strings[i]);
            values.put("TO_BASKET", 0);
            db.insert("PIZZA", null, values);
        }
        values.clear();

        values.put("IMAGE_RESOURCE_ID", R.drawable.sicilia_p);
        db.update("PIZZA", values, "_id = ?", new String[] {Integer.toString(1)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.caprichosa_p);
        db.update("PIZZA", values, "_id = ?",  new String[] {Integer.toString(2)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.marinara_p);
        db.update("PIZZA", values, "_id = ?",  new String[] {Integer.toString(3)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.napoletana_p);
        db.update("PIZZA", values, "_id = ?",  new String[] {Integer.toString(4)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.margarita_p);
        db.update("PIZZA", values, "_id = ?",  new String[] {Integer.toString(5)});
        values.clear();

        db.execSQL("CREATE TABLE BASKET ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COST TEXT);");

        name_strings = res.getStringArray(R.array.db_sushi_name);
        cost_strings = res.getStringArray(R.array.dp_sushi_cost);
        descrption_strings = res.getStringArray(R.array.db_sushi_description);

        db.execSQL("CREATE TABLE SUSHI ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COST TEXT, "
                + "DESCRIPTION TEXT, "
                + "TO_BASKET INTEGER, "
                + "IMAGE_RESOURCE_ID INTEGER);");

        for (int i = 0; i < name_strings.length; i++) {
            values.put("NAME", name_strings[i]);
            values.put("COST", cost_strings[i]);
            values.put("DESCRIPTION", descrption_strings[i]);
            values.put("TO_BASKET", 0);
            db.insert("SUSHI", null, values);
        }
        values.clear();

        values.put("IMAGE_RESOURCE_ID", R.drawable.philadelphia_s);
        db.update("SUSHI", values, "_id = ?", new String[] {Integer.toString(1)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.unagi);
        db.update("SUSHI", values, "_id = ?",  new String[] {Integer.toString(2)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.unagi_s);
        db.update("SUSHI", values, "_id = ?",  new String[] {Integer.toString(3)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.gunkantobi_s);
        db.update("SUSHI", values, "_id = ?",  new String[] {Integer.toString(4)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.phelicks_s);
        db.update("SUSHI", values, "_id = ?",  new String[] {Integer.toString(5)});
        values.clear();


        name_strings = res.getStringArray(R.array.db_drink_name);
        cost_strings = res.getStringArray(R.array.dp_drink_cost);
        descrption_strings = res.getStringArray(R.array.db_drink_description);

        db.execSQL("CREATE TABLE DRINK ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COST TEXT, "
                + "DESCRIPTION TEXT, "
                + "TO_BASKET INTEGER, "
                + "IMAGE_RESOURCE_ID INTEGER);");

        for (int i = 0; i < name_strings.length; i++) {
            values.put("NAME", name_strings[i]);
            values.put("COST", cost_strings[i]);
            values.put("DESCRIPTION", descrption_strings[i]);
            values.put("TO_BASKET", 0);
            db.insert("DRINK", null, values);
        }
        values.clear();

        values.put("IMAGE_RESOURCE_ID", R.drawable.coca_cola_d);
        db.update("DRINK", values, "_id = ?", new String[] {Integer.toString(1)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.pepsi_d);
        db.update("DRINK", values, "_id = ?",  new String[] {Integer.toString(2)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.sprite_d);
        db.update("DRINK", values, "_id = ?",  new String[] {Integer.toString(3)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.pepper_d);
        db.update("DRINK", values, "_id = ?",  new String[] {Integer.toString(4)});
        values.put("IMAGE_RESOURCE_ID", R.drawable.fanta_d);
        db.update("DRINK", values, "_id = ?",  new String[] {Integer.toString(5)});
        values.clear();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void clearBasket() {
        SQLiteDatabase db;
        db = getWritableDatabase();
        db.delete("BASKET", null, null);
    }
}
