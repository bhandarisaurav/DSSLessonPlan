package com.dss.lessonplan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dss.lessonplan.domain.LessonPlan;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lessonPlan.db";
    private static final String TABLE_NAME = "lesson_plan";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
            " Class text,\n" +
            " Subject text ,\n" +
            " Lesson text ,\n" +
            " Topic text  ,\n" +
            " Objective text  ,\n" +
            " Classwork text  ,\n" +
            " Homework text  ,\n" +
            " Time text  \n," +
            " Date text  \n" +
            ")";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertData(LessonPlan lessonPlan) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Class", lessonPlan.getAclass());
        contentValues.put("Subject", lessonPlan.getSubject());
        contentValues.put("Lesson", lessonPlan.getLesson());
        contentValues.put("Topic", lessonPlan.getTopic());
        contentValues.put("Objective", lessonPlan.getObjective());
        contentValues.put("Classwork", lessonPlan.getClasswork());
        contentValues.put("Homework", lessonPlan.getHomework());
        contentValues.put("Time", lessonPlan.getTime());
        contentValues.put("Date", lessonPlan.getDate());

        long result = sqLiteDatabase.insertWithOnConflict(TABLE_NAME, null, contentValues, CONFLICT_REPLACE);
        sqLiteDatabase.close();
        return result != -1;
    }

    public LessonPlan viewData(String aclass, String subject, String date) {
        LessonPlan lessonPlan = new LessonPlan();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where Class = ? and Subject = ? and Date = ?",
                new String[]{aclass, subject, date}, null);
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println("Class = " + aclass);
        System.out.println("Subject = " + subject);
        System.out.println("Date = " + date);
        System.out.println("++++++++++++++++++++++++++++");
        if (cursor != null && cursor.moveToFirst()) {
            lessonPlan.setAclass(cursor.getString(cursor.getColumnIndex("Class")));
            lessonPlan.setSubject(cursor.getString(cursor.getColumnIndex("Subject")));
            lessonPlan.setLesson(cursor.getString(cursor.getColumnIndex("Lesson")));
            lessonPlan.setTopic(cursor.getString(cursor.getColumnIndex("Topic")));
            lessonPlan.setObjective(cursor.getString(cursor.getColumnIndex("Objective")));
            lessonPlan.setClasswork(cursor.getString(cursor.getColumnIndex("Classwork")));
            lessonPlan.setHomework(cursor.getString(cursor.getColumnIndex("Homework")));
            lessonPlan.setTime(cursor.getString(cursor.getColumnIndex("Time")));
            lessonPlan.setDate(cursor.getString(cursor.getColumnIndex("Date")));
            cursor.close();
            return lessonPlan;
        } else {
            lessonPlan.setMessage("No Data Found");
            return lessonPlan;
        }

    }
}