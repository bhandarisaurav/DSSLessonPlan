package com.dss.lessonplan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String db_name = "lessonplan.db";
    private static final String table_name = "lesson_plan";

    public DatabaseHelper(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table_name + " (\n" +
                " Class text,\n" +
                " Subject text ,\n" +
                " Lesson text ,\n" +
                " Topic text  ,\n" +
                " Objective text  ,\n" +
                " Classwork text  ,\n" +
                " Homework text  ,\n" +
                " Time text  \n," +
                " Date text  \n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists " + table_name);

    }

    public boolean insertData(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", "x");

        long result = sqLiteDatabase.insert("x", null, contentValues);
        return result != -1;
    }
}
