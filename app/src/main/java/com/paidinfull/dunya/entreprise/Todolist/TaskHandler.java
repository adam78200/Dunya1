package com.paidinfull.dunya.entreprise.Todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TaskHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "cahierCommande";
    private static final String TABLE_NAME = "labels";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public TaskHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT ) ;";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertTask(String label) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, label);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List getAllTask() {
        List tasks = new ArrayList();

        String selectQuery = " SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tasks.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

    public Cursor getData(String name) {
        SQLiteDatabase dsf = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE NAME ='" + name + "'";
        Cursor cursor = dsf.rawQuery(query, null);
        return cursor;

    }
    public void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID ='" + id + "'";
        //Cursor curs = db.rawQuery(query, null);
        db.delete(TABLE_NAME, "NAME = ?", new String[]{id});
        //return curs;
    }
}
