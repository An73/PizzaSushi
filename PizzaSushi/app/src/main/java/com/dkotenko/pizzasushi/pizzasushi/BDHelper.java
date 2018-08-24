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
        + "IMAGE_RESOURCE_ID INTEGER);");


        //values.put("NAME", "dahfkdsahfkdshkasd");
        //db.insert("PIZZA", null, values);
        //db.update("PIZZA", values, null, null);

        for (int i = 0; i < name_strings.length; i++) {
            values.put("NAME", name_strings[i]);
            values.put("COST", cost_strings[i]);
            values.put("DESCRIPTION", descrption_strings[i]);
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

       /* pizza_strings = res.getStringArray(R.array.dp_pizza_cost);
        for (int i = 0; i < pizza_strings.length; i++) {
            values.put("COST", pizza_strings[i]);
            db.insert("PIZZA", null, values);
        }
        pizza_strings = res.getStringArray(R.array.db_pizza_description);
        for (int i = 0; i < pizza_strings.length; i++) {
            values.put("DESCRIPTION", pizza_strings[i]);
            db.insert("PIZZA", null, values);
        }*/
        /*values.put("IMAGE_RESOURCE_ID", R.drawable.sicilia_p);
        db.insert("PIZZA", null, values);
        values.put("IMAGE_RESOURCE_ID", R.drawable.caprichosa_p);
        db.insert("PIZZA", null, values);
        values.put("IMAGE_RESOURCE_ID", R.drawable.marinara_p);
        db.insert("PIZZA", null, values);
        values.put("IMAGE_RESOURCE_ID", R.drawable.napoletana_p);
        db.insert("PIZZA", null, values);
        values.put("IMAGE_RESOURCE_ID", R.drawable.margarita_p);
        db.insert("PIZZA", null, values);*/



        /*db.execSQL("CREATE TABLE SUSHI ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COST"
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");

        db.execSQL("CREATE TABLE DRINK ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COST"
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");

        db.execSQL("CREATE TABLE BASKET ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COST);");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
