package com.example.studentregistryv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String COLUMN_DATA_ID = "DATA_ID";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_STUDENT_ID = "STUDENT_ID";
    public static final String COLUMN_SEX = "SEX";

    public DatabaseHelper(@Nullable Context context) {
        super(context, STUDENT_TABLE+".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + STUDENT_TABLE + "(" + COLUMN_DATA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_STUDENT_NAME + " STRING," + COLUMN_STUDENT_ID + " STRING," + COLUMN_SEX + " STRING)";
        db.execSQL(query);
    }

    public Boolean addOne(DataModel dataModel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STUDENT_NAME,dataModel.getStudentName());
        cv.put(COLUMN_STUDENT_ID,dataModel.getStudentID());
        cv.put(COLUMN_SEX,dataModel.getSex());
        long insert = db.insert(STUDENT_TABLE, null, cv);
        if (insert ==-1){
            return false;
        }else{
            return true;
        }


    }

    public List<DataModel> getEveryone() {
        List<DataModel> returnResult = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM STUDENT_TABLE";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                int dataKey = cursor.getInt(0);
                String name = cursor.getString(1);
                String studentID = cursor.getString(2);
                String sex = cursor.getString(3);
                DataModel dataModel = new DataModel(dataKey, name, studentID, sex);
                returnResult.add(dataModel);


            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        db.close();
        return returnResult;
    }

    public Boolean deleteOne(DataModel dataModel){
    SQLiteDatabase db = getWritableDatabase();
        String delete = "DELETE FROM " +STUDENT_TABLE + " WHERE " + COLUMN_DATA_ID + " = " +dataModel.getKeyID();
        Cursor cursor = db.rawQuery(delete, null);
        if (cursor.moveToFirst()){
            return  true;
        }else{
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
